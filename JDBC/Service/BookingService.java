package service;

package com.dbproject.train_reservation_system.service;

import com.dbproject.train_reservation_system.dao.ReservationDAO;
import com.dbproject.train_reservation_system.dao.SeatDAO;
import com.dbproject_train_reservation_system.dao.TicketDAO;
import com.dbproject.train_reservation_system.dao.PaymentDAO;
import com.dbproject.train_reservation_system.model.Reservation;
import com.dbproject.train_reservation_system.model.Seat;
import com.dbproject.train_reservation_system.model.Ticket;
import com.dbproject.train_reservation_system.model.Payment;
import com.dbproject_train_reservation_system.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingService {
    private ReservationDAO reservationDAO = new ReservationDAO();
    private SeatDAO seatDAO = new SeatDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    /**
     * 4단계 트랜잭션 처리
     *
     * 1) 예약 가능한 좌석인지 확인 (SELECT … FOR UPDATE)
     * 2) 예약 생성 (Reservation INSERT)
     * 2.5) 좌석 상태를 'Booked'로 변경 (Seat UPDATE)
     * 3) 티켓 발급 (Ticket INSERT)
     * 4) 결제 처리 (Payment INSERT)
     *
     * @param passengerId 승객 ID
     * @param scheduleId  스케줄 ID
     * @param seatId      좌석 ID
     * @param travelDate  여행 날짜 (YYYY-MM-DD)
     * @param price       티켓 금액
     * @param amount      결제 금액
     * @param method      결제 방식 (예: "CARD", "CASH")
     */
    public void completeBooking(
            String passengerId,
            int scheduleId,
            int seatId,
            LocalDate travelDate,
            int price,
            int amount,
            String method
    ) {
        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // 1) 예약 가능한 좌석인지 확인 (FOR UPDATE)
            Reservation existing = reservationDAO.selectForUpdate(conn, scheduleId, seatId);
            if (existing != null) {
                throw new RuntimeException("해당 좌석은 이미 예약되었습니다.");
            }

            // 2) 예약 생성
            Reservation reservation = new Reservation(0, passengerId, scheduleId, seatId, travelDate);
            reservationDAO.insertReservation(conn, reservation);
            int newReservationId = reservation.getReservationId();
            if (newReservationId <= 0) {
                throw new RuntimeException("예약 생성에 실패했습니다.");
            }

            // 2.5) 좌석 상태를 'Booked'로 변경
            Seat seat = seatDAO.selectSeatByTrainAndNumber(conn, reservation.getScheduleId(), null);
            // Seat 조회 시 필요한 컬럼이 train_id 및 seat_number인 경우, DAO 메서드에 맞게 수정 필요
            // 예를 들어, seatId 기반 조회 메서드가 있다면 그 메서드를 사용
            seat.setAvailability("Booked");
            seatDAO.updateSeat(conn, seat);

            // 3) 티켓 발급
            Ticket ticket = new Ticket(0, newReservationId, seatId, price, LocalDateTime.now());
            ticketDAO.insertTicket(conn, ticket);
            int newTicketId = ticket.getTicketId();
            if (newTicketId <= 0) {
                throw new RuntimeException("티켓 발급 실패");
            }

            // 4) 결제 처리
            Payment payment = new Payment(0, newReservationId, LocalDateTime.now(), amount, method, "COMPLETED");
            paymentDAO.insertPayment(conn, payment);
            int newPaymentId = payment.getPaymentId();
            if (newPaymentId <= 0) {
                throw new RuntimeException("결제 실패, 예약이 취소되었습니다.");
            }

            conn.commit();
            System.out.println("=== 예약 완료 ===");
            System.out.println("Reservation ID: " + newReservationId);
            System.out.println("Ticket ID     : " + newTicketId);
            System.out.println("Payment ID    : " + newPaymentId);

        } catch (Exception ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ignored) {}
                DBUtil.closeQuietly(conn);
            }
        }
    }
}