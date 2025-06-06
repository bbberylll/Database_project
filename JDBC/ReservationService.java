package com.dbproject.train_reservation_system.service;

import com.dbproject.train_reservation_system.model.Reservation;
import com.dbproject.train_reservation_system.model.Payment;
import com.dbproject.train_reservation_system.util.DBUtil;
import com.dbproject.train_reservation_system.model.Ticket;

import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationService {
	public void reserveAndCompleteTransaction(Reservation reservation, BigDecimal amount, String method, int seatId) {
		String updateSeatSql = "UPDATE seat SET availability = 'Booked' WHERE train_id = ? AND seat_number = ? AND availability = 'Available'";
        String insertReservationSql = "INSERT INTO reservation (passenger_id, train_id, seat_number, reservation_date) VALUES (?, ?, ?, ?)";
        String insertPaymentSql = "INSERT INTO payment (reservation_id, payment_date, amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";
        String insertTicketSql = "INSERT INTO ticket (reservation_id, seat_id, price, issue_date) VALUES (?, ?, ?, ?)";

        Connection conn = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); //Start transaction
            
            //1. Check and update seat availability
            PreparedStatement seatStmt = conn.prepareStatement(updateSeatSql);
            seatStmt.setString(1, reservation.getTrainId());
            seatStmt.setString(2, reservation.getSeatNumber());
            int seatUpdated = seatStmt.executeUpdate();
            if (seatUpdated == 0) {
                throw new SQLException("Seat is already booked or does not exist.");
            }

            // 2. INSERT reservation
            PreparedStatement resStmt = conn.prepareStatement(insertReservationSql, Statement.RETURN_GENERATED_KEYS);
            resStmt.setString(1, reservation.getPassengerId());
            resStmt.setString(2, reservation.getTrainId());
            resStmt.setString(3, reservation.getSeatNumber());
            resStmt.setTimestamp(4, Timestamp.valueOf(reservation.getReservationDate()));
            resStmt.executeUpdate();

            ResultSet resKeys = resStmt.getGeneratedKeys();
            int generatedResId = -1;
            if (resKeys.next()) {
                generatedResId = resKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve reservation ID.");
            }

            // 3. INSERT payment
            PreparedStatement payStmt = conn.prepareStatement(insertPaymentSql);
            payStmt.setInt(1, generatedResId);
            payStmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            payStmt.setBigDecimal(3, amount);
            payStmt.setString(4, method);
            payStmt.setString(5, "Paid");
            payStmt.executeUpdate();
            
            //4. Insert ticket
            PreparedStatement ticketStmt = conn.prepareStatement(insertTicketSql);
            ticketStmt.setInt(1, generatedResId);
            ticketStmt.setInt(2, seatId);
            ticketStmt.setBigDecimal(3, amount); // assuming price = payment amount
            ticketStmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ticketStmt.executeUpdate();

            conn.commit(); // Commit if all operations succeed
            System.out.println("Reservation, payment, and ticket issued successfully.");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // Rollback if any error occurs
                System.out.println("Transaction failed. Rolled back. Reason: " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Failed to close DB connection: " + e.getMessage());
            }
        }
    }
	
	public void cancelReservation(int reservationId, String trainId, String seatNumber) {
	    String deleteTicketSql = "DELETE FROM ticket WHERE reservation_id = ?";
	    String deletePaymentSql = "DELETE FROM payment WHERE reservation_id = ?";
	    String deleteReservationSql = "DELETE FROM reservation WHERE reservation_id = ?";
	    String updateSeatSql = "UPDATE seat SET availability = 'Available' WHERE train_id = ? AND seat_number = ?";

	    Connection conn = null;

	    try {
	        conn = DBUtil.getConnection();
	        conn.setAutoCommit(false);

	        // 1. Delete ticket
	        try (PreparedStatement stmt = conn.prepareStatement(deleteTicketSql)) {
	            stmt.setInt(1, reservationId);
	            stmt.executeUpdate();
	        }

	        // 2. Delete payment
	        try (PreparedStatement stmt = conn.prepareStatement(deletePaymentSql)) {
	            stmt.setInt(1, reservationId);
	            stmt.executeUpdate();
	        }

	        // 3. Delete reservation
	        try (PreparedStatement stmt = conn.prepareStatement(deleteReservationSql)) {
	            stmt.setInt(1, reservationId);
	            stmt.executeUpdate();
	        }

	        // 4. Restore seat availability
	        try (PreparedStatement stmt = conn.prepareStatement(updateSeatSql)) {
	            stmt.setString(1, trainId);
	            stmt.setString(2, seatNumber);
	            stmt.executeUpdate();
	        }

	        conn.commit();
	        System.out.println("Reservation canceled and seat released.");

	    } catch (Exception e) {
	        try {
	            if (conn != null) conn.rollback();
	            System.out.println("Cancel failed and rolled back. Reason: " + e.getMessage());
	        } catch (SQLException ex) {
	            System.out.println("Rollback failed: " + ex.getMessage());
	        }
	    } finally {
	        try {
	            if (conn != null) conn.setAutoCommit(true);
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.out.println("Failed to close DB connection: " + e.getMessage());
	        }
	    }
	}

}
