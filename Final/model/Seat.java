package model;

public class Seat {
    private int seatId;            
    private String trainId;      
    private String seatNumber;     
    private String seatClass;       
    private String availability;   

    public Seat(int seatId, String trainId, String seatNumber, String seatClass, String availability) {
        this.seatId = seatId;
        this.trainId = trainId;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.availability = availability;
    }
    
    public Seat(int seatId, String trainId, String seatNumber, String availability) {
        this.seatId       = seatId;
        this.trainId      = trainId;
        this.seatNumber   = seatNumber;
        this.availability = availability;
    }

    // Getter / Setter
    public int getSeatId() {
        return seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
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

    public String getSeatClass() {
        return seatClass;
    }
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }
}