package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.TrainSchedule;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainScheduleDAO {
	// INSERT
    public void insertSchedule(TrainSchedule schedule) {
        String sql = "INSERT INTO train_schedule (train_id, departure_station_id, arrival_station_id, departure_time, arrival_time) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTrainId());
            pstmt.setString(2, schedule.getDepartureStationId());
            pstmt.setString(3, schedule.getArrivalStationId());
            pstmt.setTimestamp(4, schedule.getDepartureTime());
            pstmt.setTimestamp(5, schedule.getArrivalTime());

            pstmt.executeUpdate();
            System.out.println("Train schedule inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to insert train schedule: " + e.getMessage());
        }
    }
    // SELECT ALL
    public List<TrainSchedule> getAllSchedules() {
        List<TrainSchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM train_schedule";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TrainSchedule schedule = new TrainSchedule(
                    rs.getInt("schedule_id"),
                    rs.getString("train_id"),
                    rs.getString("departure_station_id"),
                    rs.getString("arrival_station_id"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("arrival_time")
                );
                list.add(schedule);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve train schedules: " + e.getMessage());
        }

        return list;
    }

    // UPDATE
    public void updateArrivalTime(int scheduleId, Timestamp newArrivalTime) {
        String sql = "UPDATE train_schedule SET arrival_time = ? WHERE schedule_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, newArrivalTime);
            pstmt.setInt(2, scheduleId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Arrival time updated successfully.");
            } else {
                System.out.println("No schedule found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update arrival time: " + e.getMessage());
        }
    }
    
    // DELETE
    public void deleteScheduleById(int scheduleId) {
        String sql = "DELETE FROM train_schedule WHERE schedule_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, scheduleId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Train schedule deleted successfully.");
            } else {
                System.out.println("No schedule found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete train schedule: " + e.getMessage());
        }
    }

}
