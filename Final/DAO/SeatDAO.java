package DAO;

import model.Seat;
import DB.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDAO {
	// INSERT
    public void insertSeat(Seat seat) {
        String sql = "INSERT INTO seat (train_id, seat_number, seat_class, availability) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, seat.getTrainId());
            pstmt.setString(2, seat.getSeatNumber());
            pstmt.setString(3, seat.getSeatClass());
            pstmt.setString(4, seat.getAvailability());

            pstmt.executeUpdate();
            System.out.println("Seat inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to insert seat: " + e.getMessage());
        }
    }
    
    // SELECT ALL
    public List<Seat> getAllSeats() {
        List<Seat> list = new ArrayList<>();
        String sql = "SELECT * FROM seat";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seat s = new Seat(
                    rs.getInt("seat_id"),
                    rs.getString("train_id"),
                    rs.getString("seat_number"),
                    rs.getString("seat_class"),
                    rs.getString("availability")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve seats: " + e.getMessage());
        }

        return list;
    }
    
    // UPDATE availability
    public void updateAvailability(int seatId, String newStatus) {
        String sql = "UPDATE seat SET availability = ? WHERE seat_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus); // "Available" or "Booked"
            pstmt.setInt(2, seatId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Seat availability updated successfully.");
            } else {
                System.out.println("No seat found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update seat: " + e.getMessage());
        }
    }
    
    // DELETE
    public void deleteSeatById(int seatId) {
        String sql = "DELETE FROM seat WHERE seat_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, seatId);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Seat deleted successfully.");
            } else {
                System.out.println("No seat found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete seat: " + e.getMessage());
        }
    }
    
 // ================================================
    // 2) 새로 추가: Connection을 외부에서 받아서 처리하는 메서드
    // ================================================
    /**
     * *신규* – Service 계층에서 트랜잭션 관리 시, train_id + seat_number로 조회
     * (4단계 트랜잭션 중 1번 단계 또는 1.5번 단계에서 호출)
     */
    public Seat selectSeatByTrainAndNumber(Connection conn, String trainId, String seatNumber) {
        String sql = "SELECT * FROM seat WHERE train_id = ? AND seat_number = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, trainId);
            pstmt.setString(2, seatNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Seat(
                    rs.getInt("seat_id"),
                    rs.getString("train_id"),
                    rs.getString("seat_number"),
                    rs.getString("seat_class"),
                    rs.getString("availability")
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Seat 조회 실패 (트랜잭션 공유 버전): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
            // conn은 Service에서 관리하므로 여기서 닫지 않습니다.
        }
    }

    /**
     * *신규* – Service 계층에서 트랜잭션 관리 시, Seat 객체의 availability를 업데이트
     * (4단계 트랜잭션 중 2번 단계 혹은 3번 단계 이후 호출)
     */
    public void updateSeat(Connection conn, Seat seat) {
        String sql = "UPDATE seat SET train_id = ?, seat_number = ?, seat_class = ?, availability = ? WHERE seat_id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, seat.getTrainId());
            pstmt.setString(2, seat.getSeatNumber());
            pstmt.setString(3, seat.getSeatClass());
            pstmt.setString(4, seat.getAvailability());
            pstmt.setInt(5, seat.getSeatId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Seat update 실패 (트랜잭션 공유 버전): " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(pstmt);
            // conn은 Service에서 관리하므로 여기서 닫지 않습니다.
        }
    }
    
    
    /** 좌석 ID로 조회 */
    public Seat selectSeatById(Connection conn, int seatId) {
        String sql = "SELECT seat_id, train_id, seat_number, availability "
                   + "FROM seat WHERE seat_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, seatId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Seat(
                        rs.getInt("seat_id"),
                        rs.getString("train_id"),
                        rs.getString("seat_number"),
                        rs.getString("availability")
                    );
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Seat 조회 실패: " + e.getMessage(), e);
        }
    }

}
