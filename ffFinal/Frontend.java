package Fronted;

import service.PassengerService;
import service.TrainService;
import service.BookingService;

import model.Passenger;
import model.Train;
import model.Reservation;
import model.TrainSchedule;

import exception.BusinessException;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Frontend {
    private final Scanner scanner = new Scanner(System.in);
    
    private final PassengerService passengerService = new PassengerService();
    private final BookingService bookingService = new BookingService();
    private final TrainService trainService = new TrainService();


    

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
                case 8 -> findTrainsByDestination();
                case 9 -> checkReservedSeatCount();
                case 10 -> updateDepartureTime();
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
        String trainName = scanner.nextLine().trim();
        
        if (trainName.isEmpty()) {
            System.out.println("기차 이름은 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            List<TrainSchedule> list = trainService.findSchedulesByTrainName(trainName);
            if (list.isEmpty()) {
                System.out.println("해당 기차명으로 스케줄이 없습니다: " + trainName);
            } else {
                System.out.println("===== 기차 스케줄 =====");
                for (TrainSchedule ts : list) {
                    System.out.printf("ID:%d, 출발역:%s → 도착역:%s, 출발:%s, 도착:%s%n",
                        ts.getScheduleId(),
                        ts.getDepartureStationId(),
                        ts.getArrivalStationId(),
                        ts.getDepartureTime(),
                        ts.getArrivalTime()
                    );
                }
            }
        } catch (BusinessException e) {
            System.out.println("[스케줄 조회 실패] " + e.getMessage());
        }
        
    }
    
    // 3. 목적지별 예약된 승객 수 조회
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
    
    // 4. 승객 이메일 수정
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

    // 5. 승객 삭제
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

    // 6. 기차 등록
    private void insertTrain() {
        System.out.print("열차의 ID를 입력하세요: ");
        String trainID = scanner.nextLine();
        if (trainID.isEmpty()) {
            System.out.println("열차 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("열차 이름: ");
        String trainName = scanner.nextLine();
        if (trainName.isEmpty()) {
            System.out.println("열차 이름은 비어 있을 수 없습니다.");
            return;
        }

        System.out.print("열차 타입: ");
        String trainType = scanner.nextLine();
        if (trainType.isEmpty()) {
            System.out.println("열차 타입은 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("열차 출발역 ID: ");
        String trainDepID = scanner.nextLine();
        if (trainDepID.isEmpty()) {
            System.out.println("열차 출발역 ID는 비어 있을 수 없습니다.");
            return;
        }

        System.out.print("열차 도착역 ID: ");
        String trainArrID = scanner.nextLine();
        if (trainArrID.isEmpty()) {
            System.out.println("열차 도착역 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("출발 시간: ");
        String depTimeStr = scanner.nextLine();
        if (depTimeStr.isEmpty()) {
            System.out.println("출발 시간은 비어 있을 수 없습니다.");
            return;
        }

        
        System.out.print("도착 시간: ");
        String arrIDStr = scanner.nextLine();
        if (arrIDStr.isEmpty()) {
            System.out.println("도착 시간은 비어 있을 수 없습니다.");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime depTime = LocalDateTime.parse(depTimeStr, formatter);
            LocalDateTime arrTime = LocalDateTime.parse(arrIDStr, formatter);

            Train t = new Train(trainID, trainName, trainType, trainDepID, trainArrID, depTime, arrTime);
            trainService.addTrain(t);
            System.out.println("[열차 등록 성공]");

        } catch (DateTimeParseException e) {
            System.out.println("[시간 형식 오류] yyyy-MM-dd HH:mm:ss 형식으로 입력해주세요.");
        } catch (BusinessException e) {
            System.out.println("[열차 등록 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }

    }

    // 7. 모든 기차 목록 조회
    private void listAllTrains() {
        try {
            List<Train> trains = trainService.listAllTrains();

            if (trains.isEmpty()) {
                System.out.println("🚫 등록된 열차가 없습니다.");
                return;
            }

            System.out.println("등록된 열차 목록: ");
            for (Train train : trains) {
                System.out.println("-----------------------------------");
                System.out.println("열차 ID: " + train.getTrainId());
                System.out.println("열차 이름: " + train.getTrainName());
                System.out.println("열차 종류: " + train.getTrainType());
                System.out.println("출발역 ID: " + train.getDepartureStationId());
                System.out.println("도착역 ID: " + train.getArrivalStationId());
                System.out.println("출발 시간: " + train.getDepartureTime());
                System.out.println("도착 시간: " + train.getArrivalTime());
            }
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("[열차 목록 조회 실패] " + e.getMessage());
        }
    }

    // 8. 목적지 기준 기차 조회
        private void findTrainsByDestination() {
        System.out.print("조회할 목적지 역 ID를 입력하세요: ");
        String destination = scanner.nextLine();

        if (destination.isEmpty()) {
            System.out.println("❗️ 목적지 역 ID는 비어 있을 수 없습니다.");
            return;
        }

        try {
            List<Train> trains = trainService.findTrainsByDestination(destination);

            if (trains.isEmpty()) {
                System.out.println("해당 목적지로 가는 열차가 없습니다.");
                return;
            }

            System.out.println("목적지 [" + destination + "]로 가는 열차 목록:");
            for (Train train : trains) {
                System.out.println("-----------------------------------");
                System.out.println("열차 ID: " + train.getTrainId());
                System.out.println("열차 이름: " + train.getTrainName());
                System.out.println("열차 종류: " + train.getTrainType());
                System.out.println("출발역 ID: " + train.getDepartureStationId());
                System.out.println("출발 시간: " + train.getDepartureTime());
                System.out.println("도착 시간: " + train.getArrivalTime());
            }
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("[기차 조회 실패] " + e.getMessage());
        }
    }
    
    // 9. 기차별 예약된 좌석 수 조회
    private void checkReservedSeatCount() {
        System.out.print("조회할 열차의 ID를 입력하세요: ");
        String trainId = scanner.nextLine();

        if (trainId.isEmpty()) {
            System.out.println("❗️ 열차 ID는 비어 있을 수 없습니다.");
            return;
        }

        try {
            int count = trainService.getReservedSeatCount(trainId);
            System.out.println("열차 ID [" + trainId + "] 예약된 좌석 수: " + count + "개");
        } catch (Exception e) {
            System.out.println("[좌석 수 조회 실패] " + e.getMessage());
        }
    }

    // 10. 기차 출발시간 수정
    private void updateDepartureTime() {
        System.out.print("수정할 열차의 ID를 입력하세요: ");
        String trainId = scanner.nextLine();
        
        if (trainId.isEmpty()) {
            System.out.println("❗️ 열차 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        System.out.print("새 출발 시간을 입력하세요: ");
        String newDepTimeStr = scanner.nextLine();
        
        if (newDepTimeStr.isEmpty()) {
            System.out.println("❗️ 출발 시간은 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime newDepTime = LocalDateTime.parse(newDepTimeStr, formatter);

            trainService.updateDepartureTime(trainId, newDepTime);
            System.out.println("[시간 수정 성공]");
        } catch (DateTimeParseException e) {
            System.out.println("[시간 형식 오류] 형식은 yyyy-MM-dd HH:mm:ss 여야 합니다.");
        } catch (BusinessException e) {
            System.out.println("[시간 수정 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }
        
    }

    // 11. 기차 삭제
    private void deleteTrainById() {
        System.out.print("삭제할 열차의 ID를 입력하세요: ");
        String trainId = scanner.nextLine();
        
        if (trainId.isEmpty()) {
            System.out.println("❗️ 열차 ID는 비어 있을 수 없습니다.");
            return;
        }
        
        try {
            trainService.deleteTrain(trainId);
            System.out.println("[열차 삭제 성공]");
        } catch (BusinessException e) {
            System.out.println("[열차 삭제 실패] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[알 수 없는 오류] " + e.getMessage());
        }
    }
    
}
