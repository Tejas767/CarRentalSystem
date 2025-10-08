package com.CarRentalSystem.CarRentalSystem.controller;


import com.CarRentalSystem.CarRentalSystem.model.CarEntity;
import com.CarRentalSystem.CarRentalSystem.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarRepository carRepo;

    @PostMapping
    public String addCar(@RequestBody CarEntity car) {
        boolean exists = carRepo.existsByBrandAndModelAndYear(
                car.getBrand(),
                car.getModel(),
                car.getYear()
        );

        if (exists) {
            return "Car already exists!";
        }

        carRepo.save(car);
        return "✅ Car added successfully!";
    }

    @GetMapping
    public List<CarEntity> getCars() {
        return carRepo.findAll();
    }

    @GetMapping("/available")
    public List<CarEntity> getAvailableCars() {
        return carRepo.findAll()
                .stream()
                .filter(CarEntity::isAvailable)
                .toList();
    }
    // ✅ Admin cancels a booking by ID
    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        if (!carRepo.existsById(id)) {
            return "Car with ID " + id + " not found!";
        }

        try {
            // Attempt to delete the car
            carRepo.deleteById(id);
            return "✅ Car with ID " + id + " deleted successfully!";
        } catch (Exception e) {
            // Catch the database exception
            return "Error: Cannot delete Car ID " + id + ". It has existing bookings (active or history). To delete the car, you must first delete all associated bookings.";
        }
    }

    @PutMapping("/{id}")
    public String updateCar(@PathVariable Long id, @RequestBody CarEntity updatedCar) {
        return carRepo.findById(id)
                .map(car -> {
                    car.setBrand(updatedCar.getBrand());
                    car.setModel(updatedCar.getModel());
                    car.setType(updatedCar.getType());
                    car.setYear(updatedCar.getYear());
                    car.setFuelType(updatedCar.getFuelType());
                    car.setPricePerDay(updatedCar.getPricePerDay());
                    car.setAvailable(updatedCar.isAvailable());
                    carRepo.save(car);
                    return "✅ Car updated successfully!";
                })
                .orElse("Car with ID " + id + " not found!");
    }





}
