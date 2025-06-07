package model;

import java.time.LocalDateTime;

public class Ticket {
    private int ticketId;        
    private int reservationId;     
    private int seatId;            
    private int price;             
    private LocalDateTime issueDate;  

    public Ticket(int ticketId, int reservationId, int seatId, int price, LocalDateTime issueDate) {
        this.ticketId = ticketId;
        this.reservationId = reservationId;
        this.seatId = seatId;
        this.price = price;
        this.issueDate = issueDate;
    }

        public int getTicketId() {
        return ticketId;
    }
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getReservationId() {
        return reservationId;
    }
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getSeatId() {
        return seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }
}