package DAO;

import model.Station;
import DB.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationDAO {
	// INSERT
    public void insertStation(Station station) {
        String sql = "INSERT INTO station (station_id, station_name, location) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, station.getStationId());
            pstmt.setString(2, station.getStationName());
            pstmt.setString(3, station.getLocation());

            pstmt.executeUpdate();
            System.out.println("Station inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to insert station: " + e.getMessage());
        }
    }

    // SELECT ALL
    public List<Station> getAllStations() {
        List<Station> list = new ArrayList<>();
        String sql = "SELECT * FROM station";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Station station = new Station(
                    rs.getString("station_id"),
                    rs.getString("station_name"),
                    rs.getString("location")
                );
                list.add(station);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve stations: " + e.getMessage());
        }

        return list;
    }

    // UPDATE
    public void updateStationName(String stationId, String newName) {
        String sql = "UPDATE station SET station_name = ? WHERE station_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, stationId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Station name updated successfully.");
            } else {
                System.out.println("No station found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update station: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteStationById(String stationId) {
        String sql = "DELETE FROM station WHERE station_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stationId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Station deleted successfully.");
            } else {
                System.out.println("No station found to delete.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete station: " + e.getMessage());
        }
    }

}
