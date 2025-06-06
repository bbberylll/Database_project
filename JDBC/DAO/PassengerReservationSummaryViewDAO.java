package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.dto.PassengerReservationSummaryDTO;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PassengerReservationSummaryViewDAO {
	public List<PassengerReservationSummaryDTO> getAllSummaries() {
        List<PassengerReservationSummaryDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM passenger_reservation_summary_view";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PassengerReservationSummaryDTO dto = new PassengerReservationSummaryDTO(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("seat_number"),
                    rs.getTimestamp("reservation_date").toLocalDateTime(),
                    rs.getString("payment_status")
                );
                list.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve passenger reservation summary: " + e.getMessage());
        }

        return list;
    }

}
