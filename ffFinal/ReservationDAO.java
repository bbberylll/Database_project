package DAO;

import model.Reservation;
import DB.DBUtil;
import DTO.ReservationCountByTrain;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    // ============================
    // 기본 CRUD 메서드
    // ============================
    // INSERT
    public void insertReservation(Reservation r) {
        String sql = "INSERT INTO reservation (passenger_id, train_id, seat_number, reservation_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    // SELECT ALL
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Reservation(
                    rs.getInt("reservation_id"),
                    rs.getString("passenger_id"),
                    rs.getString("train_id"),
                    rs.getString("seat_number"),
                    rs.getTimestamp("reservation_date").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("전체 예약 조회 실패: " + e.getMessage(), e);
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
            if (result == 0) {
                System.out.println("삭제할 예약이 없습니다: ID=" + reservationId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 삭제 실패: " + e.getMessage(), e);
        }
    }

    // UPDATE seat_number (기존)
    public void updateSeatNumber(int reservationId, String newSeatNumber, String trainId) {
        String checkSql = "SELECT availability FROM seat WHERE train_id = ? AND seat_number = ?";
        String updateSql = "UPDATE reservation SET seat_number = ? WHERE reservation_id = ?";
        try (Connection conn = DBUtil.getConnection()) {
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, trainId);
                checkStmt.setString(2, newSeatNumber);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next() || !"Available".equalsIgnoreCase(rs.getString("availability"))) {
                        throw new RuntimeException("해당 좌석을 예약할 수 없습니다.");
                    }
                }
            }
            try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                pstmt.setString(1, newSeatNumber);
                pstmt.setInt(2, reservationId);
                int updated = pstmt.executeUpdate();
                if (updated == 0) {
                    System.out.println("수정할 예약이 없습니다: ID=" + reservationId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("좌석 번호 수정 실패: " + e.getMessage(), e);
        }
    }

    // ============================
    // 트랜잭션 공유용 메서드
    // ============================
    public void insertReservation(Connection conn, Reservation r) {
        insertReservation(r); // 기존 insert 재사용
    }

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
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 조회 실패: " + e.getMessage(), e);
        }
        return null;
    }

    public void deleteReservationById(Connection conn, int reservationId) {
        deleteReservationById(reservationId); // 기존 delete 재사용
    }

    public Reservation selectForUpdate(Connection conn, int scheduleId, int seatId) {
        String sql = "SELECT * FROM reservation WHERE schedule_id = ? AND seat_id = ? FOR UPDATE";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, seatId);
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
            }
        } catch (SQLException e) {
            throw new RuntimeException("예약 잠금 조회 실패: " + e.getMessage(), e);
        }
        return null;
    }

    // ============================
    // 추가 조회 기능
    // ============================
    public List<Reservation> searchScheduleByTrainName(String trainName) {
        String sql = "SELECT r.* FROM reservation r JOIN train t ON r.train_id = t.train_id WHERE t.train_name = ?";
        List<Reservation> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trainName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("passenger_id"),
                        rs.getString("train_id"),
                        rs.getString("seat_number"),
                        rs.getTimestamp("reservation_date").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("스케줄 조회 실패: " + e.getMessage(), e);
        }
        return list;
    }

    public int countPassengersByDestination(String destination) {
        String sql = ""
          + "SELECT COUNT(*) AS cnt\n"
          + "FROM reservation r\n"
          + "JOIN train_schedule ts\n"
          + "  ON r.train_schedule_id = ts.schedule_id\n"
          + "JOIN station s\n"
          + "  ON ts.arrival_station_id = s.station_id\n"
          + "WHERE s.station_name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destination);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("cnt") : 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("목적지별 예약 수 조회 실패: " + e.getMessage(), e);
        }
    }

    public List<ReservationCountByTrain> countReservationsByTrain() {
        String sql = "SELECT train_id, COUNT(*) AS count FROM reservation GROUP BY train_id";
        List<ReservationCountByTrain> result = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                result.add(new ReservationCountByTrain(
                    rs.getString("train_id"),
                    rs.getInt("count")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("기차별 예약 수 조회 실패: " + e.getMessage(), e);
        }
        return result;
    }
    
    
}
