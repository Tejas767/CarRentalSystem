package com.CarRentalSystem.CarRentalSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"brand", "model", "year"})
})
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String type;
    private int year;
    private String fuelType;
    private double pricePerDay;
    private boolean available;

    public CarEntity() {}

    public CarEntity(String brand, String model, String type, int year,
                     String fuelType, double pricePerDay, boolean available) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.fuelType = fuelType;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
