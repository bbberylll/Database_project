package Fronted;

import service.PassengerService;
import model.Passenger;
import model.Train;
import model.Reservation;
import service.BookingService;
import exception.BusinessException;
import java.util.Scanner;

public class Frontend {
    private final Scanner scanner = new Scanner(System.in);
    
    private final PassengerService passengerService = new PassengerService();
    private final BookingService bookingService = new BookingService();

    

    public void start() {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> insertPassenger();
                case 2 -> searchTrainScheduleByName();
                case 3 -> showPassengerCountByDestination();
                case 4 -> updatePassengerEmail();
                case 5 -> deletePassengerByName();
                case 6 -> insertTrain();
                case 7 -> listAllTrains();
                case 8 -> searchTrainsByDestination();
                case 9 -> showReservationCountByTrain();
                case 10 -> updateTrainDepartureTime();
                case 11 -> deleteTrainById();
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("잘못된 항목을 선택하였습니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                <==== 기차표 예약 시스템 ====>
                1. 승객 등록 (INSERT)
                2. 기차 이름으로 스케줄 조회 (JOIN + VIEW)
                3. 목적지별 예약된 승객 수 조회 (GROUP BY)
                4. 승객 이메일 수정 (UPDATE)
                5. 승객 삭제 (DELETE)
                6. 기차 정보 등록 (INSERT)
                7. 모든 기차 목록 조회 (SELECT)
                8. 목적지 기준 기차 조회 (SELECT + 사용자 입력)
                9. 기차별 예약된 좌석 수 조회 (SELECT + GROUP BY)
                10. 기차 출발 시간 수정 (UPDATE + 사용자 입력)
                11. 기차 삭제 (DELETE + 사용자 입력)
                0. 종료
                선택 >> 
                """);
    }

    private void insertPassenger() {
        System.out.print("승객의 ID를 입력하세요 (예: P100): ");
        String id = scanner.nextLine();
        if (id.isEmpty()) {
            System.out.println("승객 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("이름: ");
        String firstName = scanner.nextLine();
        if (firstName.isEmpty()) {
            System.out.println("이름은 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("성: ");
        String lastName = scanner.nextLine();
        if (lastName.isEmpty()) {
            System.out.println("성은 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        if (email.isEmpty()) {
            System.out.println("이메일은 비어 있을 수 없습니다.");
            return;
        }

        
        System.out.print("전화번호: ");
        String phone = scanner.nextLine();
        if (phone.isEmpty()) {
            System.out.println("전화번호는 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            Passenger p = new Passenger(id, firstName, lastName, email, phone);
            passengerService.addPassenger(p);
            System.out.println("[승객 등록 성공]");
        } catch (BusinessException e) {
            System.out.println("[승객 등록 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }

    }

    private void searchTrainScheduleByName() {
        System.out.print("기차 이름을 입력하세요: ");
        String trainName = scanner.nextLine();
        
        if (trainName.isEmpty()) {
            System.out.println("기차 이름은 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            bookingService.showScheduleByTrainName(trainName);
        } catch (BusinessException e) {
            System.out.println("[스케줄 조회 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }
        
    }

    private void showPassengerCountByDestination() {
        System.out.print("목적지를 입력하세요: ");
        String destination = scanner.nextLine();
        
        if (destination.isEmpty()) {
            System.out.println("❗️ 목적지는 비어 있을 수 없습니다.");
            return;
        }

        try {
            bookingService.countPassengersByDestination(destination);
        } catch (BusinessException e) {
            System.out.println("[조회 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }

    }

    private void updatePassengerEmail() {
        System.out.print("이메일을 수정할 승객의 ID를 입력하세요: ");
        String passengerId = scanner.nextLine();
        
        if (passengerId.isEmpty()) {
            System.out.println("❗️ 승객 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("새 이메일 주소를 입력하세요: ");
        String newEmail = scanner.nextLine();
        
        if (newEmail.isEmpty()) {
            System.out.println("❗️ 이메일은 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            passengerService.updatePassengerEmail(passengerId, newEmail);
            System.out.println("[이메일 수정 성공]");
        } catch (BusinessException e) {
            System.out.println("[이메일 수정 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }
        
    }


    private void deletePassengerByName() {
        System.out.print("삭제할 승객의 ID를 입력하세요: ");
        String passengerId = scanner.nextLine();
        
        if (passengerId.isEmpty()) {
            System.out.println("❗️ 승객 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            passengerService.deletePassenger(passengerId);
            System.out.println("[승객 삭제 성공]");
        } catch (BusinessException e) {
            System.out.println("[승객 삭제 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }
    }

    private void insertTrain() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            System.out.print("Train ID: ");
            String trainId = scanner.nextLine();
            System.out.print("Train Name: ");
            String name = scanner.nextLine();
            System.out.print("Source: ");
            String source = scanner.nextLine();
            System.out.print("Destination: ");
            String destination = scanner.nextLine();
            System.out.print("Departure Time (YYYY-MM-DD HH:MM:SS): ");
            String departureTime = scanner.nextLine();

            String sql = "INSERT INTO train (train_id, name, source, destination, departure_time) VALUES (?, ?, ?, ?, ?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, trainId);
            stmt.setString(2, name);
            stmt.setString(3, source);
            stmt.setString(4, destination);
            stmt.setString(5, departureTime);
            int rows = stmt.executeUpdate();
            System.out.println(rows + "개의 기차 정보가 등록되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listAllTrains() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            var stmt = conn.prepareStatement("SELECT * FROM train");
            var rs = stmt.executeQuery();
            System.out.println("Train ID | Name | Source | Destination | Departure Time");
            while (rs.next()) {
                System.out.printf("%s | %s | %s | %s | %s%n", rs.getString("train_id"), rs.getString("name"), rs.getString("source"), rs.getString("destination"), rs.getString("departure_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchTrainsByDestination() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            System.out.print("목적지를 입력하세요: ");
            String destination = scanner.nextLine();
            var stmt = conn.prepareStatement("SELECT * FROM train WHERE destination = ?");
            stmt.setString(1, destination);
            var rs = stmt.executeQuery();
            System.out.println("Train ID | Name | Source | Destination | Departure Time");
            while (rs.next()) {
                System.out.printf("%s | %s | %s | %s | %s%n", rs.getString("train_id"), rs.getString("name"), rs.getString("source"), rs.getString("destination"), rs.getString("departure_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showReservationCountByTrain() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            var stmt = conn.prepareStatement("SELECT train_id, COUNT(*) AS count FROM reservation GROUP BY train_id");
            var rs = stmt.executeQuery();
            System.out.println("Train ID | 예약된 좌석 수");
            while (rs.next()) {
                System.out.printf("%s | %d%n", rs.getString("train_id"), rs.getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTrainDepartureTime() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            conn.setAutoCommit(false);
            System.out.print("수정할 기차 ID: ");
            String trainId = scanner.nextLine();
            System.out.print("새 출발시간 (YYYY-MM-DD HH:MM:SS): ");
            String newTime = scanner.nextLine();

            var stmt = conn.prepareStatement("UPDATE train SET departure_time = ? WHERE train_id = ?");
            stmt.setString(1, newTime);
            stmt.setString(2, trainId);
            int rows = stmt.executeUpdate();
            conn.commit();
            System.out.println(rows + "개의 기차 출발시간이 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteTrainById() {
        try (var conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/train_db", "root", "password")) {
            System.out.print("삭제할 기차 ID: ");
            String trainId = scanner.nextLine();
            var stmt = conn.prepareStatement("DELETE FROM train WHERE train_id = ?");
            stmt.setString(1, trainId);
            int rows = stmt.executeUpdate();
            System.out.println(rows + "개의 기차 정보가 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
