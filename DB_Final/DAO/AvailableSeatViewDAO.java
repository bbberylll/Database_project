package DAO;

import DTO.AvailableSeatDTO;
import DB.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvailableSeatViewDAO {
	public List<AvailableSeatDTO> getAllAvailableSeats() {
        List<AvailableSeatDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM available_seats_view";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AvailableSeatDTO dto = new AvailableSeatDTO(
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("seat_number"),
                    rs.getString("seat_class"),
                    rs.getString("availability")
                );
                list.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve available seats: " + e.getMessage());
        }

        return list;
    }
	
}
