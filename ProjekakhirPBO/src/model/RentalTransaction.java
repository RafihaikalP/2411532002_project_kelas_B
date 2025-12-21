package model;

import java.time.LocalDateTime;

public class RentalTransaction {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime; // null kalau belum dikembalikan
    private double cost;

    private Customer customer;
    private PlayStation playStation;

    public RentalTransaction(String id, LocalDateTime startTime, LocalDateTime endTime,
                             double cost, Customer customer, PlayStation playStation) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.customer = customer;
        this.playStation = playStation;
    }

    public String getId() { return id; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public double getCost() { return cost; }
    public Customer getCustomer() { return customer; }
    public PlayStation getPlayStation() { return playStation; }

    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "RentalTransaction{id='" + id + "', customer=" + customer.getName() +
                ", ps=" + playStation.getType() + ", start=" + startTime +
                ", end=" + endTime + ", cost=" + cost + "}";
    }
}
