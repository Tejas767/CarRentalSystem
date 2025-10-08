package com.CarRentalSystem.CarRentalSystem.dto;

import java.time.LocalDate;

public class BookingResponseDTO {
    private Long id;
    private String username;
    private String carName;
    private String fuelType;
    private double pricePerDay;
    private int year;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private double totalCost;
    private String status;
    private String pickupLocation;
    private String dropoffLocation;

    // Default constructor required for JSON serialization
    public BookingResponseDTO() {}

    //  All-args constructor
    public BookingResponseDTO(Long id, String username, String carName,
                              String fuelType, double pricePerDay, int year,
                              LocalDate rentalStartDate, LocalDate rentalEndDate,
                              double totalCost, String status,
                              String pickupLocation, String dropoffLocation) {
        this.id = id;
        this.username = username;
        this.carName = carName;
        this.fuelType = fuelType;
        this.pricePerDay = pricePerDay;
        this.year = year;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalCost = totalCost;
        this.status = status;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
    }

    //  Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getCarName() { return carName; }
    public String getFuelType() { return fuelType; }
    public double getPricePerDay() { return pricePerDay; }
    public int getYear() { return year; }
    public LocalDate getRentalStartDate() { return rentalStartDate; }
    public LocalDate getRentalEndDate() { return rentalEndDate; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
    public String getPickupLocation() { return pickupLocation; }
    public String getDropoffLocation() { return dropoffLocation; }

    //  (Optional) Setters – in case i need to modify the object after creation
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setCarName(String carName) { this.carName = carName; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setYear(int year) { this.year = year; }
    public void setRentalStartDate(LocalDate rentalStartDate) { this.rentalStartDate = rentalStartDate; }
    public void setRentalEndDate(LocalDate rentalEndDate) { this.rentalEndDate = rentalEndDate; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public void setStatus(String status) { this.status = status; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }
}
