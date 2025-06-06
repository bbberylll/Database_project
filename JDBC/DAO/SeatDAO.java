package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.Seat;
import com.dbproject.train_reservation_system.util.DBUtil;

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

}
