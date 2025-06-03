//하단 코드는 참고 및 예시용. 실제 사용 위해서는 정비 및 추가 필요.

public Passenger getPassengerById(String id) {
    String query = "SELECT * FROM passenger WHERE passenger_id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Passenger(
                rs.getString("passenger_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

public void updatePassengerEmail(String passengerId, String newEmail) {
    String query = "UPDATE passenger SET email = ? WHERE passenger_id = ?";

    try (Connection conn = DBConnection.getConnection()) {
        conn.setAutoCommit(false);  // 트랜잭션 시작

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newEmail);
            stmt.setString(2, passengerId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                conn.commit();
                System.out.println("이메일이 성공적으로 업데이트되었습니다.");
            } else {
                conn.rollback();
                System.out.println("업데이트할 승객이 없습니다.");
            }
        } catch (SQLException e) {
            conn.rollback();
            System.err.println("E)이메일 업데이트 중 오류가 발생하였습니다: " + e.getMessage());
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}