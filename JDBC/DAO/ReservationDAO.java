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

}
