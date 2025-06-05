package com.dbproject.train_reservation_system.model;

import java.sql.Timestamp;

public class Train {
	private String trainId;
    private String trainName;
    private String trainType;
    private String departureStationId;
    private String arrivalStationId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public Train(String trainId, String trainName, String trainType,
                 String departureStationId, String arrivalStationId,
                 Timestamp departureTime, Timestamp arrivalTime) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.trainType = trainType;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }
    
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    
    public String getTrainType() { return trainType; }
    public void setTrainType(String trainType) { this.trainType = trainType; }
    
    public String getDepartureStationId() { return departureStationId; }
    public void setDepartureStationId(String departureStationId) { this.departureStationId = departureStationId; }
    
    public String getArrivalStationId() { return arrivalStationId; }
    public void setArrivalStationId(String arrivalStationId) { this.arrivalStationId = arrivalStationId; }
    
    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }
    
    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }

}
