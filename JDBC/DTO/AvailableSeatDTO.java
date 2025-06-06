package com.dbproject.train_reservation_system.dto;

public class AvailableSeatDTO {
	private String trainId;
    private String trainName;
    private String seatNumber;
    private String seatClass;
    private String availability;

    public AvailableSeatDTO(String trainId, String trainName, String seatNumber, String seatClass, String availability) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.availability = availability;
    }

    public String getTrainId() { return trainId; }
    public String getTrainName() { return trainName; }
    public String getSeatNumber() { return seatNumber; }
    public String getSeatClass() { return seatClass; }
    public String getAvailability() { return availability; }

    public void setTrainId(String trainId) { this.trainId = trainId; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public void setSeatClass(String seatClass) { this.seatClass = seatClass; }
    public void setAvailability(String availability) { this.availability = availability; }

}
