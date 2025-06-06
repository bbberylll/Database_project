package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.Reservation;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
	// INSERT
    public void insertReservation(Reservation r) {
        String sql = "INSERT INTO reservation (passenger_id, train_id, seat_number, reservation_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, r.getPassengerId());
            pstmt.setString(2, r.getTrainId());
            pstmt.setString(3, r.getSeatNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(r.getReservationDate()));

            pstmt.executeUpdate();
            System.out.println("Reservation inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to insert reservation: " + e.getMessage());
        }
    }

    // SELECT ALL
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getString("passenger_id"),
                    rs.getString("train_id"),
                    rs.getString("seat_number"),
                    rs.getTimestamp("reservation_date").toLocalDateTime()
                );
                list.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve reservations: " + e.getMessage());
        }

        return list;
    }
    
    // DELETE
    public void deleteReservationById(int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationId);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Reservation deleted successfully.");
            } else {
                System.out.println("No reservation found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete reservation: " + e.getMessage());
        }
    }
    
    // UPDATE seat_number only if the new seat is available
    public void updateSeatNumber(int reservationId, String newSeatNumber, String trainId) {
        String checkSql = "SELECT availability FROM seat WHERE train_id = ? AND seat_number = ?";
        String updateSql = "UPDATE reservation SET seat_number = ? WHERE reservation_id = ?";

        try (Connection conn = DBUtil.getConnection()) {

            // 1. Check seat availability
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, trainId);
                checkStmt.setString(2, newSeatNumber);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    String status = rs.getString("availability");
                    if (!status.equalsIgnoreCase("Available")) {
                        System.out.println("The seat is already booked. Please choose another seat.");
                        return;
                    }
                } else {
                    System.out.println("Seat does not exist.");
                    return;
                }
            }

            // 2. Perform update if seat is available
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, newSeatNumber);
                pstmt.setInt(2, reservationId);

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    System.out.println("Seat number updated successfully.");
                } else {
                    System.out.println("No reservation found with the given ID.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to update seat number: " + e.getMessage());
        }
    }
    /**
     * *** 추가 ***
     * Service 계층에서 Connection을 열어 트랜잭션을 제어할 수 있도록, 
     * Connection을 외부에서 받는 버전의 insertReservation 메서드
     */
    public void insertReservation(Connection conn, Reservation r) {
        String sql = "INSERT INTO reservation (passenger_id, train_id, seat_number, reservation_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rsKeys = null;

        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, r.getPassengerId());
            pstmt.setString(2, r.getTrainId());
            pstmt.setString(3, r.getSeatNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(r.getReservationDate()));

            pstmt.executeUpdate();
            rsKeys = pstmt.getGeneratedKeys();
            if (rsKeys.next()) {
                r.setReservationId(rsKeys.getInt(1));  // *** 신규: 생성된 ID를 객체에 세팅
            }

        } catch (SQLException e) {
            throw new RuntimeException("예약 추가 실패 (트랜잭션 공유 버전): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rsKeys);
            DBUtil.closeQuietly(pstmt);
            // conn은 닫지 않음 (서비스가 관리)
        }
    }

    /**
     * *** 추가 ***
     * Connection을 외부에서 받아서 특정 ID의 Reservation을 조회
     * (트랜잭션 안에서 조회 용도)
     */
    public Reservation selectReservationById(Connection conn, int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getString("passenger_id"),
                    rs.getString("train_id"),
                    rs.getString("seat_number"),
                    rs.getTimestamp("reservation_date").toLocalDateTime()
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 조회 실패 (트랜잭션 공유 버전): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
            // conn은 닫지 않음
        }
    }

    /**
     * *** 추가 ***
     * Connection을 외부에서 받아서 특정 ID의 Reservation을 삭제
     * (트랜잭션 안에서 삭제 용도)
     */
    public void deleteReservationById(Connection conn, int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("예약 삭제 실패 (트랜잭션 공유 버전): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(pstmt);
            // conn은 닫지 않음
        }
    }

 // 예약 시 특정 schedule_id, seat_id의 행을 FOR UPDATE 해서 잠금
    public Reservation selectForUpdate(Connection conn, int scheduleId, int seatId) {
        String sql = "SELECT * FROM reservation WHERE schedule_id = ? AND seat_id = ? FOR UPDATE";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, seatId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getString("passenger_id"),
                    rs.getInt("schedule_id"),
                    rs.getInt("seat_id"),
                    rs.getDate("travel_date").toLocalDate()
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 가능좌석 확인 실패 (FOR UPDATE): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
            // conn은 서비스에서 관리하므로 닫으면 안 됨
        }
    }

    /** *추가* – Service 계층에서 하나의 Connection을 공유하며 Reservation INSERT */
    public void insertReservation(Connection conn, Reservation r) {
        String sql = "INSERT INTO reservation (passenger_id, schedule_id, seat_id, travel_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, r.getPassengerId());
            pstmt.setInt(2, r.getScheduleId());
            pstmt.setInt(3, r.getSeatId());
            pstmt.setDate(4, Date.valueOf(r.getTravelDate()));
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                r.setReservationId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 생성 실패: " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
        }
    }

    /** *추가* – Service 계층에서 하나의 Connection을 공유하며 ID로 예약 조회 */
    public Reservation selectReservationById(Connection conn, int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getString("passenger_id"),
                    rs.getInt("schedule_id"),
                    rs.getInt("seat_id"),
                    rs.getDate("travel_date").toLocalDate()
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Reservation ID 조회 실패: " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
        }
    }

    /** *추가* – Service 계층에서 하나의 Connection을 공유하며 ID로 예약 삭제 */
    public void deleteReservationById(Connection conn, int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("예약 삭제 실패: " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(pstmt);
        }
    }    
    
    
}
