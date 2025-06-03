package model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private String passengerId;
    private String trainId;
    private String seatNumber;
    private LocalDateTime reservationDate;

    public Reservation(int reservationId, String passengerId, String trainId, String seatNumber, LocalDateTime reservationDate) {
        this.reservationId = reservationId;
        this.passengerId = passengerId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.reservationDate = reservationDate;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }

    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
}