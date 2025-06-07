package DTO;

import java.time.LocalDateTime;

public class TrainScheduleDetailDTO {
	private String trainId;
    private String trainName;
    private String departureStation;
    private String arrivalStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public TrainScheduleDetailDTO(String trainId, String trainName,
            String departureStation, String arrivalStation,
            LocalDateTime departureTime, LocalDateTime arrivalTime) {
    	this.trainId = trainId;
    	this.trainName = trainName;
    	this.departureStation = departureStation;
    	this.arrivalStation = arrivalStation;
    	this.departureTime = departureTime;
    	this.arrivalTime = arrivalTime;
    }

    public String getTrainId() { return trainId; }
    public String getTrainName() { return trainName; }
    public String getDepartureStation() { return departureStation; }
    public String getArrivalStation() { return arrivalStation; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }

    public void setTrainId(String trainId) { this.trainId = trainId; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public void setDepartureStation(String departureStation) { this.departureStation = departureStation; }
    public void setArrivalStation(String arrivalStation) { this.arrivalStation = arrivalStation; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

}
