package DAO;

import model.Train;
import DB.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class TrainDAO {
    // ============================
    // 기존 CRUD 메서드
    // ============================
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
            pstmt.setTimestamp(6, Timestamp.valueOf(train.getDepartureTime()));
            pstmt.setTimestamp(7, Timestamp.valueOf(train.getArrivalTime()));
            pstmt.executeUpdate();
            System.out.println("Train inserted successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("Train 삽입 실패: " + e.getMessage(), e);
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
                list.add(new Train(
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("train_type"),
                    rs.getString("departure_station_id"),
                    rs.getString("arrival_station_id"),
                    rs.getTimestamp("departure_time").toLocalDateTime(),
                    rs.getTimestamp("arrival_time").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("전체 기차 조회 실패: " + e.getMessage(), e);
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
            if (result == 0) {
                System.out.println("수정할 기차가 없습니다: ID=" + trainId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("기차 이름 수정 실패: " + e.getMessage(), e);
        }
    }

    // UPDATE departure time
    public void updateDepartureTime(String trainId, LocalDateTime newTime) {
        String sql = "UPDATE train SET departure_time = ? WHERE train_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(newTime));
            pstmt.setString(2, trainId);
            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("수정할 기차가 없습니다: ID=" + trainId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("출발 시간 수정 실패: " + e.getMessage(), e);
        }
    }

    // DELETE
    public void deleteTrainById(String trainId) {
        String sql = "DELETE FROM train WHERE train_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trainId);
            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("삭제할 기차가 없습니다: ID=" + trainId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("기차 삭제 실패: " + e.getMessage(), e);
        }
    }

    // ============================
    // 트랜잭션 공유용 오버로드 메서드
    // ============================
    public void insertTrain(Connection conn, Train train) {
        String sql = "INSERT INTO train (train_id, train_name, train_type, departure_station_id, arrival_station_id, departure_time, arrival_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, train.getTrainId());
            pstmt.setString(2, train.getTrainName());
            pstmt.setString(3, train.getTrainType());
            pstmt.setString(4, train.getDepartureStationId());
            pstmt.setString(5, train.getArrivalStationId());
            pstmt.setTimestamp(6, Timestamp.valueOf(train.getDepartureTime()));
            pstmt.setTimestamp(7, Timestamp.valueOf(train.getArrivalTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("트랜잭션용 기차 삽입 실패: " + e.getMessage(), e);
        }
    }

    public List<Train> getAllTrains(Connection conn) {
        List<Train> list = new ArrayList<>();
        String sql = "SELECT * FROM train";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Train(
                    rs.getString("train_id"),
                    rs.getString("train_name"),
                    rs.getString("train_type"),
                    rs.getString("departure_station_id"),
                    rs.getString("arrival_station_id"),
                    rs.getTimestamp("departure_time").toLocalDateTime(),
                    rs.getTimestamp("arrival_time").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("트랜잭션용 전체 기차 조회 실패: " + e.getMessage(), e);
        }
        return list;
    }

    public void updateDepartureTime(Connection conn, String trainId, LocalDateTime newTime) {
        String sql = "UPDATE train SET departure_time = ? WHERE train_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, Timestamp.valueOf(newTime));
            pstmt.setString(2, trainId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("트랜잭션용 출발 시간 수정 실패: " + e.getMessage(), e);
        }
    }

    public void deleteTrainById(Connection conn, String trainId) {
        String sql = "DELETE FROM train WHERE train_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trainId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("트랜잭션용 기차 삭제 실패: " + e.getMessage(), e);
        }
    }

    // ============================
    // 추가 조회 기능
    // ============================
    public List<Train> findByDestination(String destination) {
        String sql = "SELECT * FROM train WHERE arrival_station_id = ?";
        List<Train> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, destination);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Train(
                        rs.getString("train_id"),
                        rs.getString("train_name"),
                        rs.getString("train_type"),
                        rs.getString("departure_station_id"),
                        rs.getString("arrival_station_id"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("목적지별 기차 조회 실패: " + e.getMessage(), e);
        }
        return list;
    }

}
