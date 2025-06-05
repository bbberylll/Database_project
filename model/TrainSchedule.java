package com.dbproject.train_reservation_system.model;

import java.sql.Timestamp;

public class TrainSchedule {
	private int scheduleId;
    private String trainId;
    private String departureStationId;
    private String arrivalStationId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;

    public TrainSchedule(int scheduleId, String trainId, String departureStationId, String arrivalStationId,
                         Timestamp departureTime, Timestamp arrivalTime) {
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    
    public String getTrainId() { return trainId; }
    public void setTrainId(String trainId) { this.trainId = trainId; }
    
    public String getDepartureStationId() { return departureStationId; }
    public void setDepartureStationId(String departureStationId) { this.departureStationId = departureStationId; }
    
    public String getArrivalStationId() { return arrivalStationId; }
    public void setArrivalStationId(String arrivalStationId) { this.arrivalStationId = arrivalStationId; }
    
    public Timestamp getDepartureTime() { return departureTime; }
    public void setDepartureTime(Timestamp departureTime) { this.departureTime = departureTime; }
    
    public Timestamp getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Timestamp arrivalTime) { this.arrivalTime = arrivalTime; }

}
