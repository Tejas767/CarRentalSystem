package com.CarRentalSystem.CarRentalSystem.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double totalCost;
    private String status;
    private String pickupLocation;
    private String dropoffLocation;

    public BookingEntity() {}

    public BookingEntity(UserEntity user, CarEntity car,
                         LocalDate rentalStartDate, LocalDate rentalEndDate,
                         double totalCost, String status,
                         String pickupLocation, String dropoffLocation) {
        this.user = user;
        this.car = car;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalCost = totalCost;
        this.status = status;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
    }

    // getters & setters
    public Long getId() { return id; }
    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }
    public CarEntity getCar() { return car; }
    public void setCar(CarEntity car) { this.car = car; }
    public LocalDate getRentalStartDate() { return rentalStartDate; }
    public void setRentalStartDate(LocalDate rentalStartDate) { this.rentalStartDate = rentalStartDate; }
    public LocalDate getRentalEndDate() { return rentalEndDate; }
    public void setRentalEndDate(LocalDate rentalEndDate) { this.rentalEndDate = rentalEndDate; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }
}

