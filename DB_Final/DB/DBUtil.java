package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://192.168.1.103:3306/db_project";
    private static final String USER = "bibidi";
    private static final String PASSWORD = "Bibidi123!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC 드라이버 로드 실패", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /**
     * AutoCloseable (Statement, ResultSet 등)을 예외 없이 닫아주는 유틸 메서드
     */
    public static void closeQuietly(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                // 로그를 남기고 싶으면 여기에 작성, 아니면 무시
            }
        }
    }
}