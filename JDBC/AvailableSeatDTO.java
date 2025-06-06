package com.dbproject.train_reservation_system.dto;

public class AvailableSeatDTO {
	private String trainId;
    private String seatNumber;
    private String seatClass;
    private String availability;

    public AvailableSeatDTO(String trainId, String seatNumber, String seatClass, String availability) {
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.availability = availability;
    }

    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getSeatClass() { return seatClass; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

}
