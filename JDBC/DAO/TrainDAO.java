package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.Train;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {
	// INSERT
    public void insertTrain(Train train) {
        String sql = "INSERT INTO train (train_id, train_name, train_type, departure_station_id, arrival_station_id, departure_time, arrival_time) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, train.getTrainId());
            pstmt.setString(2, train.getTrainName());
            pstmt.setString(3, train.getTrainType());
            pstmt.setString(4, train.getDepartureStationId());
            pstmt.setString(5, train.getArrivalStationId());
            pstmt.setTimestamp(6, train.getDepartureTime());
            pstmt.setTimestamp(7, train.getArrivalTime());

            pstmt.executeUpdate();
            System.out.println("Train inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to insert train: " + e.getMessage());
        }
    }

    // SELECT ALL
    public List<Train> getAllTrains() {
        List<Train> list = new ArrayList<>();
        String sql = "SELECT * FROM train";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Train t = new Train(
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("train_type"),
                    rs.getString("departure_station_id"),
                    rs.getString("arrival_station_id"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("arrival_time")
                );
                list.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve trains: " + e.getMessage());
        }

        return list;
    }

    // UPDATE train name
    public void updateTrainName(String trainId, String newName) {
        String sql = "UPDATE train SET train_name = ? WHERE train_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, trainId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Train name updated successfully.");
            } else {
                System.out.println("No train found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update train: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteTrainById(String trainId) {
        String sql = "DELETE FROM train WHERE train_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainId);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Train deleted successfully.");
            } else {
                System.out.println("No train found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete train: " + e.getMessage());
        }
    }

}
