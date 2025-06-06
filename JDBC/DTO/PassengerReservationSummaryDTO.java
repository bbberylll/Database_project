package com.dbproject.train_reservation_system.dto;

import java.time.LocalDateTime;

public class PassengerReservationSummaryDTO {
	private String firstName;
    private String lastName;
    private String trainId;
    private String trainName;
    private String seatNumber;
    private LocalDateTime reservationDate;
    private String paymentStatus;

    public PassengerReservationSummaryDTO(String firstName, String lastName, String trainId, String trainName,
            String seatNumber, LocalDateTime reservationDate, String paymentStatus) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.trainId = trainId;
    	this.trainName = trainName;
    	this.seatNumber = seatNumber;
    	this.reservationDate = reservationDate;
    	this.paymentStatus = paymentStatus;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getTrainId() { return trainId; }
    public String getTrainName() { return trainName; }
    public String getSeatNumber() { return seatNumber; }
    public LocalDateTime getReservationDate() { return reservationDate; }
    public String getPaymentStatus() { return paymentStatus; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setTrainId(String trainId) { this.trainId = trainId; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public void setReservationDate(LocalDateTime reservationDate) { this.reservationDate = reservationDate; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

}
