package DAO;

import model.Ticket;
import DB.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
	// INSERT
    public void insertTicket(Ticket t) {
        String sql = "INSERT INTO ticket (reservation_id, seat_id, price, issue_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, t.getReservationId());
            pstmt.setInt(2, t.getSeatId());
            pstmt.setInt(3, t.getPrice());
            pstmt.setTimestamp(4, Timestamp.valueOf(t.getIssueDate()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    int generatedId = keys.getInt(1);
                    System.out.println("Ticket inserted with ID: " + generatedId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to insert ticket: " + e.getMessage());
        }
    }

    // SELECT ALL
    public List<Ticket> getAllTickets() {
        List<Ticket> list = new ArrayList<>();
        String sql = "SELECT * FROM ticket";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ticket t = new Ticket(
                    rs.getInt("ticket_id"),
                    rs.getInt("reservation_id"),
                    rs.getInt("seat_id"),
                    rs.getInt("price"),
                    rs.getTimestamp("issue_date").toLocalDateTime()
                );
                list.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve tickets: " + e.getMessage());
        }

        return list;
    }
    
    // SELECT ONE
    public Ticket getTicketById(int ticketId) {
        String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticketId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Ticket(
                    rs.getInt("ticket_id"),
                    rs.getInt("reservation_id"),
                    rs.getInt("seat_id"),
                    rs.getInt("price"),
                    rs.getTimestamp("issue_date").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve ticket: " + e.getMessage());
        }

        return null;
    }
    
    // UPDATE
    public void updateTicket(Ticket ticket) {
        String sql = "UPDATE ticket SET reservation_id = ?, seat_id = ?, price = ?, issue_date = ? WHERE ticket_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticket.getReservationId());
            pstmt.setInt(2, ticket.getSeatId());
            pstmt.setInt(3, ticket.getPrice());
            pstmt.setTimestamp(4, Timestamp.valueOf(ticket.getIssueDate()));
            pstmt.setInt(5, ticket.getTicketId());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Ticket updated successfully.");
            } else {
                System.out.println("No ticket found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update ticket: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteTicketById(int ticketId) {
        String sql = "DELETE FROM ticket WHERE ticket_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticketId);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Ticket deleted successfully.");
            } else {
                System.out.println("No ticket found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete ticket: " + e.getMessage());
        }
        
    
    }
    
    // ================================================
    // 2) 새로 추가된 메서드: Connection을 외부에서 받아서 처리
    //    → Service 계층에서 트랜잭션을 제어할 때 사용
    // ================================================

    /**
     * *신규* – Service 계층에서 하나의 Connection을 공유하며 티켓을 INSERT
     * (4단계 트랜잭션 중 3번 단계에서 호출)
     */
    public void insertTicket(Connection conn, Ticket t) {
        String sql = "INSERT INTO ticket (reservation_id, seat_id, price, issue_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, t.getReservationId());
            pstmt.setInt(2, t.getSeatId());
            pstmt.setInt(3, t.getPrice());
            pstmt.setTimestamp(4, Timestamp.valueOf(t.getIssueDate()));
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                t.setTicketId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("티켓 발급 실패: " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
            // conn은 Service에서 관리하므로 여기서 닫지 않는다.
        }
    }
    
}
