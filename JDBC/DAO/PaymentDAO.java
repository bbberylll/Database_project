package com.dbproject.train_reservation_system.dao;

import com.dbproject.train_reservation_system.model.Payment;
import com.dbproject.train_reservation_system.util.DBUtil;

import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
	// INSERT
    public void insertPayment(Payment payment) {
        String sql = "INSERT INTO payment (reservation_id, payment_date, amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, payment.getReservationId());
            pstmt.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDate()));
            pstmt.setBigDecimal(3, payment.getAmount());
            pstmt.setString(4, payment.getPaymentMethod());
            pstmt.setString(5, payment.getPaymentStatus());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    System.out.println("Payment inserted. ID: " + keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to insert payment: " + e.getMessage());
        }
    }

    // SELECT ALL
    public List<Payment> getAllPayments() {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payment";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Payment p = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("reservation_id"),
                    rs.getTimestamp("payment_date").toLocalDateTime(),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("payment_status")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve payments: " + e.getMessage());
        }

        return list;
    }

    // UPDATE status
    public void updatePaymentStatus(int paymentId, String newStatus) {
        String sql = "UPDATE payment SET payment_status = ? WHERE payment_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, paymentId);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Payment status updated.");
            } else {
                System.out.println("No payment found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update payment status: " + e.getMessage());
        }
    }

    // DELETE
    public void deletePaymentById(int paymentId) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, paymentId);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("Payment deleted successfully.");
            } else {
                System.out.println("No payment found with the given ID.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete payment: " + e.getMessage());
        }
    }
    
    // SELECT payment by reservation_id
    public Payment getPaymentByReservationId(int reservationId) {
        String sql = "SELECT * FROM payment WHERE reservation_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("reservation_id"),
                    rs.getTimestamp("payment_date").toLocalDateTime(),
                    rs.getBigDecimal("amount"),
                    rs.getString("payment_method"),
                    rs.getString("payment_status")
                );
            } else {
                System.out.println("No payment found for reservation ID: " + reservationId);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve payment: " + e.getMessage());
        }

        return null;
    }
    
 // ================================================
    // 2) 새로 추가된 메서드: Connection을 외부에서 받아서 처리
    //    → Service 계층에서 트랜잭션을 제어할 때 사용
    // ================================================

    /**
     * *** 신규 *** 
     * Service 계층에서 하나의 Connection을 공유하며 결제 INSERT
     * (4단계 트랜잭션 중 4번 단계에서 호출)
     */
    public void insertPayment(Connection conn, Payment payment) {
        String sql = "INSERT INTO payment (reservation_id, payment_date, amount, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, payment.getReservationId());
            pstmt.setTimestamp(2, Timestamp.valueOf(payment.getPaymentDate()));
            pstmt.setBigDecimal(3, payment.getAmount());
            pstmt.setString(4, payment.getPaymentMethod());
            pstmt.setString(5, payment.getPaymentStatus());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setPaymentId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("결제 처리 실패: " + e.getMessage(), e);
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(pstmt);
            // conn은 Service에서 관리하므로 여기서 닫지 않습니다.
        }
    }

}
