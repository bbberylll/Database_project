package DAO;

import model.Reservation;
import DB.DBUtil;

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
    // ================================================
    // 트랜잭션 공유용 메서드 (Connection 외부 관리)
    // ================================================

    /** 예약 추가 (트랜잭션 공유) */
    public void insertReservation(Connection conn, Reservation r) {
        String sql = "INSERT INTO reservation (passenger_id, train_id, seat_number, reservation_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, r.getPassengerId());
            pstmt.setString(2, r.getTrainId());
            pstmt.setString(3, r.getSeatNumber());
            pstmt.setTimestamp(4, Timestamp.valueOf(r.getReservationDate()));
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) r.setReservationId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 생성 실패: " + e.getMessage(), e);
        }
    }

    /** 예약 조회 (트랜잭션 공유) */
    public Reservation selectReservationById(Connection conn, int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_id"),
                        rs.getString("train_id"),
                        rs.getString("seat_number"),
                        rs.getTimestamp("reservation_date").toLocalDateTime()
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Reservation ID 조회 실패: " + e.getMessage(), e);
        }
    }

    /** 예약 삭제 (트랜잭션 공유) */
    public void deleteReservationById(Connection conn, int reservationId) {
        String sql = "DELETE FROM reservation WHERE reservation_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("예약 삭제 실패: " + e.getMessage(), e);
        }
    }

    /** FOR UPDATE 잠금 조회 */
    public Reservation selectForUpdate(Connection conn, int reservationId) {
        String sql = "SELECT * FROM reservation WHERE reservation_id = ? FOR UPDATE";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_id"),
                        rs.getString("train_id"),
                        rs.getString("seat_number"),
                        rs.getTimestamp("reservation_date").toLocalDateTime()
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 잠금 조회 실패 (FOR UPDATE): " + e.getMessage(), e);
        }
    }
    
    /** 목적지별 예약된 승객 수 조회 (GROUP BY) */
    public int countByDestination(String destination) {
        String sql = """
            SELECT COUNT(*) cnt
              FROM reservation r
              JOIN train_schedule ts ON r.schedule_id = ts.schedule_id
              JOIN station s ON ts.arrival_station_id = s.station_id
             WHERE s.station_name = ?
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, destination);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("목적지별 승객 수 조회 실패: " + e.getMessage(), e);
        }
    }
    
    public Reservation selectForUpdate(Connection conn, int scheduleId, int seatId) {
        String sql = "SELECT * FROM reservation WHERE schedule_id = ? AND seat_id = ? FOR UPDATE";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, seatId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // 트랜잭션용 생성자 사용
                    return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_id"),
                        rs.getInt("schedule_id"),
                        rs.getInt("seat_id"),
                        rs.getDate("travel_date").toLocalDate()
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 잠금 조회 실패: " + e.getMessage(), e);
        }
    }
    
    
}
