package DAO;

import DTO.TrainScheduleDetailDTO;
import DB.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class TrainScheduleDetailViewDAO {
	public List<TrainScheduleDetailDTO> getAllDetailedSchedules() {
        List<TrainScheduleDetailDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM detailed_train_schedule_view";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	TrainScheduleDetailDTO dto = new TrainScheduleDetailDTO(
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("departure_station"),
                    rs.getString("arrival_station"),
                    rs.getTimestamp("departure_time") != null ? rs.getTimestamp("departure_time").toLocalDateTime() : null,
                    rs.getTimestamp("arrival_time") != null ? rs.getTimestamp("arrival_time").toLocalDateTime() : null
                );
                list.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve detailed train schedules: " + e.getMessage());
        }

        return list;
    }

}
