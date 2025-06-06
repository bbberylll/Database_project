package com.dbproject.train_reservation_system.dto;

import java.time.LocalDateTime;

public class PassengerReservationSummaryDTO {
	private String passengerId;
    private String fullName;
    private String trainId;
    private String seatNumber;
    private LocalDateTime reservationDate;

    public PassengerReservationSummaryDTO(String passengerId, String fullName,
                                          String trainId, String seatNumber, LocalDateTime reservationDate) {
        this.passengerId = passengerId;
        this.fullName = fullName;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.reservationDate = reservationDate;
    }

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public LocalDateTime getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }

}
