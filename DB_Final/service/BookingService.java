package service;

import DAO.PassengerDAO;
import DAO.TrainScheduleDAO;
import DAO.ReservationDAO;
import DAO.SeatDAO;
import DAO.TicketDAO;
import DAO.PaymentDAO;

import model.Passenger;
import model.TrainSchedule;
import model.Reservation;
import model.Seat;
import model.Ticket;
import model.Payment;

import DB.DBUtil;
import exception.BusinessException;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingService {
    private final PassengerDAO passengerDAO      = new PassengerDAO();
    private final TrainScheduleDAO scheduleDAO   = new TrainScheduleDAO();
    private final ReservationDAO reservationDAO  = new ReservationDAO();
    private final SeatDAO seatDAO                = new SeatDAO();
    private final TicketDAO ticketDAO            = new TicketDAO();
    private final PaymentDAO paymentDAO          = new PaymentDAO();

    /** 1) 승객 등록 */
    public void addPassenger(Passenger p) throws BusinessException {
        try {
            passengerDAO.insertPassenger(p);
            System.out.println("[승객 등록 완료] ID=" + p.getPassengerId());
        } catch (RuntimeException e) {
            throw new BusinessException("승객 등록 중 오류: " + e.getMessage(), e);
        }
    }

    /** 2) 기차 이름으로 스케줄 조회 */
    public void showScheduleByTrainName(String trainName) throws BusinessException {
        try {
            List<TrainSchedule> list = scheduleDAO.getSchedulesByTrainName(trainName);
            if (list.isEmpty()) {
                System.out.println("해당 기차명으로 스케줄이 없습니다: " + trainName);
                return;
            }
            System.out.println("[스케줄 조회 결과]");
            for (TrainSchedule ts : list) {
                System.out.printf("ID:%d, Train:%s, From:%s, To:%s, Depart:%s, Arrive:%s%n",
                    ts.getScheduleId(), ts.getTrainId(),
                    ts.getDepartureStationId(), ts.getArrivalStationId(),
                    ts.getDepartureTime(), ts.getArrivalTime());
            }
        } catch (RuntimeException e) {
            throw new BusinessException("스케줄 조회 중 오류: " + e.getMessage(), e);
        }
    }

    /** 3) 목적지별 예약된 승객 수 조회 */
    public void countPassengersByDestination(String destination) throws BusinessException {
        try {
            int count = reservationDAO.countPassengersByDestination(destination);
            System.out.println("[목적지별 예약 승객 수] " + destination + " -> " + count + "명");
        } catch (RuntimeException e) {
            throw new BusinessException("승객 수 조회 중 오류: " + e.getMessage(), e);
        }
    }

    /** 4) 승객 이메일 수정 */
    public void updatePassengerEmail(String passengerId, String newEmail) throws BusinessException {
        try {
            passengerDAO.updatePassengerEmail(passengerId, newEmail);
            System.out.println("[이메일 수정 완료] ID=" + passengerId);
        } catch (RuntimeException e) {
            throw new BusinessException("이메일 수정 중 오류: " + e.getMessage(), e);
        }
    }

    /** 5) 승객 삭제 */
    public void deletePassenger(String passengerId) throws BusinessException {
        try {
            passengerDAO.deletePassengerById(passengerId);
            System.out.println("[승객 삭제 완료] ID=" + passengerId);
        } catch (RuntimeException e) {
            throw new BusinessException("승객 삭제 중 오류: " + e.getMessage(), e);
        }
    }

    /** 6) 4단계 트랜잭션 처리 예약 및 발권 */
    public void completeBooking(
            String passengerId,
            int scheduleId,
            int seatId,
            LocalDate travelDate,
            int price,
            int amount,
            String method
    ) throws BusinessException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // 1) 예약 가능 여부 확인
            Reservation existing = reservationDAO.selectForUpdate(conn, scheduleId, seatId);
            if (existing != null) {
                throw new BusinessException("해당 좌석은 이미 예약되었습니다.");
            }

            // 2) 예약 생성
            Reservation reservation = new Reservation(0, passengerId, scheduleId, seatId, travelDate);
            reservationDAO.insertReservation(conn, reservation);
            int newReservationId = reservation.getReservationId();
            if (newReservationId <= 0) {
                throw new BusinessException("예약 생성 실패");
            }

            // 2.5) 좌석 상태 변경
            Seat seat = seatDAO.selectSeatById(conn, seatId);
            seat.setAvailability("Booked");
            seatDAO.updateSeat(conn, seat);

            // 3) 티켓 발급
            Ticket ticket = new Ticket(0, newReservationId, seatId, price, LocalDateTime.now());
            ticketDAO.insertTicket(conn, ticket);
            int newTicketId = ticket.getTicketId();
            if (newTicketId <= 0) {
                throw new BusinessException("티켓 발급 실패");
            }

            // 4) 결제 처리
            BigDecimal bdAmount = BigDecimal.valueOf(amount);
            Payment payment = new Payment(
                0,
                newReservationId,
                LocalDateTime.now(),
                bdAmount,
                method,
                "COMPLETED"
            );
            paymentDAO.insertPayment(conn, payment);
            int newPaymentId = payment.getPaymentId();
            if (newPaymentId <= 0) {
                throw new BusinessException("결제 실패, 예약 취소됨");
            }

            conn.commit();
            System.out.println("=== 예약 완료 ===");
            System.out.printf("Reservation ID:%d, Ticket ID:%d, Payment ID:%d%n",
                              newReservationId, newTicketId, newPaymentId);

        } catch (BusinessException be) {
            rollback(conn);
            throw be;
        } catch (Exception ex) {
            rollback(conn);
            throw new BusinessException(ex.getMessage(), ex);
        } finally {
            resetAutoCommit(conn);
            DBUtil.closeQuietly(conn);
        }
    }

    private void rollback(Connection conn) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ignored) {}
    }

    private void resetAutoCommit(Connection conn) {
        try {
            if (conn != null) conn.setAutoCommit(true);
        } catch (SQLException ignored) {}
    }
}