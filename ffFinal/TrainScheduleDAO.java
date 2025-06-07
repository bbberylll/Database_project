package DAO;

import model.TrainSchedule;
import DB.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrainScheduleDAO {

    // INSERT
    public void insertSchedule(TrainSchedule schedule) {
        String sql = """
            INSERT INTO train_schedule
               (train_id, departure_station_id, arrival_station_id, departure_time, arrival_time)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, schedule.getTrainId());
            pstmt.setString(2, schedule.getDepartureStationId());
            pstmt.setString(3, schedule.getArrivalStationId());
            pstmt.setTimestamp(4, Timestamp.valueOf(schedule.getDepartureTime()));
            pstmt.setTimestamp(5, Timestamp.valueOf(schedule.getArrivalTime()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Train schedule INSERT 실패: " + e.getMessage(), e);
        }
    }

    // SELECT ALL
    public List<TrainSchedule> getAllSchedules() {
        String sql = "SELECT * FROM train_schedule";
        List<TrainSchedule> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRowToSchedule(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("전체 스케줄 조회 실패: " + e.getMessage(), e);
        }

        return list;
    }

    // DELETE
    public void deleteScheduleById(int scheduleId) {
        String sql = "DELETE FROM train_schedule WHERE schedule_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, scheduleId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("스케줄 삭제 실패: " + e.getMessage(), e);
        }
    }

    // UPDATE (arrival_time 예시)
    public void updateArrivalTime(int scheduleId, LocalDateTime newArrivalTime) {
        String sql = "UPDATE train_schedule SET arrival_time = ? WHERE schedule_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTimestamp(1, Timestamp.valueOf(newArrivalTime));
            pstmt.setInt(2, scheduleId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("도착 시간 업데이트 실패: " + e.getMessage(), e);
        }
    }

    /** 기차 이름으로 스케줄 조회 */
    public List<TrainSchedule> getSchedulesByTrainName(String trainName) {
        String sql = """
            SELECT ts.schedule_id,
                   ts.train_id,
                   ts.departure_station_id,
                   ts.arrival_station_id,
                   ts.departure_time,
                   ts.arrival_time
              FROM train_schedule ts
              JOIN train t ON ts.train_id = t.train_id
             WHERE t.train_name = ?
            """;

        List<TrainSchedule> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, trainName.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToSchedule(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("기차명별 스케줄 조회 실패: " + e.getMessage(), e);
        }
        return list;
    }

    /** ResultSet → TrainSchedule 매핑 헬퍼 */
    private TrainSchedule mapRowToSchedule(ResultSet rs) throws SQLException {
        return new TrainSchedule(
            rs.getInt("schedule_id"),
            rs.getString("train_id"),
            rs.getString("departure_station_id"),
            rs.getString("arrival_station_id"),
            rs.getTimestamp("departure_time").toLocalDateTime(),
            rs.getTimestamp("arrival_time").toLocalDateTime()
        );
    }
}
