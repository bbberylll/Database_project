package ui;

import JDBC.ReservationService;
import java.util.Scanner;

public class Frontend {
    private final Scanner scanner = new Scanner(System.in);
    private final ReservationService service = new ReservationService();

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
                0. 종료
                선택 >> 
                """);
    }

    private void insertPassenger() {
        System.out.print("승객의 ID를 입력하세요 (예: P100): ");
        String id = scanner.nextLine();
        System.out.print("이름: ");
        String firstName = scanner.nextLine();
        System.out.print("성: ");
        String lastName = scanner.nextLine();
        System.out.print("이메일: ");
        String email = scanner.nextLine();
        System.out.print("전화번호: ");
        String phone = scanner.nextLine();

        service.addPassenger(id, firstName, lastName, email, phone);
    }

    private void searchTrainScheduleByName() {
        System.out.print("기차 이름을 입력하세요: ");
        String trainName = scanner.nextLine();
        service.showScheduleByTrainName(trainName);
    }

    private void showPassengerCountByDestination() {
        System.out.print("목적지를 입력하세요: ");
        String destination = scanner.nextLine();
        service.countPassengersByDestination(destination);
    }

    private void updatePassengerEmail() {
        System.out.print("이메일을 수정할 승객의 ID를 입력하세요: ");
        String passengerId = scanner.nextLine();
        System.out.print("새 이메일 주소를 입력하세요: ");
        String newEmail = scanner.nextLine();
        service.updatePassengerEmail(passengerId, newEmail);
    }


    private void deletePassengerByName() {
        System.out.print("삭제할 승객의 ID를 입력하세요: ");
        String passengerId = scanner.nextLine();
        service.deletePassenger(passengerId);
    }
}