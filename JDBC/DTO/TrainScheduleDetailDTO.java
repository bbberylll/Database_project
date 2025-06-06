package com.dbproject.train_reservation_system.dto;

import java.sql.Timestamp;

public class TrainScheduleDetailDTO {
	private String trainId;
    private String trainName;
    private String trainType;
    private String departureStationName;
    private String arrivalStationName;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public TrainScheduleDetailDTO(String trainId, String trainName, String trainType,
                                  String departureStationName, String arrivalStationName,
                                  Timestamp departureTime, Timestamp arrivalTime) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.trainType = trainType;
        this.departureStationName = departureStationName;
        this.arrivalStationName = arrivalStationName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getTrainType() { return trainType; }
    public void setTrainType(String trainType) { this.trainType = trainType; }

    public String getDepartureStationName() { return departureStationName; }
    public void setDepartureStationName(String departureStationName) { this.departureStationName = departureStationName; }

    public String getArrivalStationName() { return arrivalStationName; }
    public void setArrivalStationName(String arrivalStationName) { this.arrivalStationName = arrivalStationName; }

    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }

    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }

}
