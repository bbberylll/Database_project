package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.Passenger;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {
	public void insertPassenger(Passenger p) {
        String sql = "INSERT INTO passenger (passenger_id, first_name, last_name, email, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getPassengerId());
            pstmt.setString(2, p.getFirstName());
            pstmt.setString(3, p.getLastName());
            pstmt.setString(4, p.getEmail());
            pstmt.setString(5, p.getPhoneNumber());

            pstmt.executeUpdate();
            System.out.println("Passenger inserted successfully.");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Passenger ID already exists. Please use a unique ID.");
        } catch (SQLException e) {
            System.out.println("Failed to insert passenger: " + e.getMessage());
        }
    }
	
	public void updatePassengerEmail(String passengerId, String newEmail) {
	    String sql = "UPDATE passenger SET email = ? WHERE passenger_id = ?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, newEmail);
	        pstmt.setString(2, passengerId);

	        int result = pstmt.executeUpdate();
	        if (result > 0) {
	            System.out.println("Email updated successfully");
	        } else {
	            System.out.println("No passenger found with the given ID.");
	        }

	    } catch (SQLException e) {
	        System.out.println("Failed to update email: " + e.getMessage());
	    }
	}
	
	public void deletePassengerById(String passengerId) {
	    String sql = "DELETE FROM passenger WHERE passenger_id = ?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, passengerId);
	        int result = pstmt.executeUpdate();

	        if (result > 0) System.out.println("Passenger deleted successfully.");
	        else System.out.println("No passenger found to delete.");

	    } catch (SQLException e) {
	        System.out.println("Failed to delete passenger: " + e.getMessage());
	    }
	}

    // SELECT
    public List<Passenger> getAllPassengers() {
        List<Passenger> list = new ArrayList<>();
        String sql = "SELECT * FROM passenger";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Passenger p = new Passenger(
                    rs.getString("passenger_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_number")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve passengers: " + e.getMessage());
        }

        return list;
    }
	
}
