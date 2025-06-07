package model;

import java.time.LocalDateTime;
import java.time.LocalDate;


public class Reservation {
	private int reservationId;      
    private String passengerId;       
    // 트랜잭션용 필드
    private Integer scheduleId;      
    private Integer seatId;          
    private LocalDate travelDate;    

    // 조회·출력을 위한 필드
    private String trainId;           
    private String seatNumber;        
    private LocalDateTime reservationDate; 

    // 1) 예약 기본 INSERT/SELECT용 생성자
    public Reservation(int reservationId,
                       String passengerId,
                       String trainId,
                       String seatNumber,
                       LocalDateTime reservationDate) {
        this.reservationId   = reservationId;
        this.passengerId     = passengerId;
        this.trainId         = trainId;
        this.seatNumber      = seatNumber;
        this.reservationDate = reservationDate;
    }

    // 2) 트랜잭션 공유용 생성자 (여행 날짜 기준)
    public Reservation(int reservationId,
                       String passengerId,
                       int scheduleId,
                       int seatId,
                       LocalDate travelDate) {
        this.reservationId = reservationId;
        this.passengerId   = passengerId;
        this.scheduleId    = scheduleId;
        this.seatId        = seatId;
        this.travelDate    = travelDate;
    }
    

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getTrainId() {
        return trainId;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}