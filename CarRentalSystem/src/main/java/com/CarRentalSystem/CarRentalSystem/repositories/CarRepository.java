package com.CarRentalSystem.CarRentalSystem.repositories;

import com.CarRentalSystem.CarRentalSystem.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    boolean existsByBrandAndModelAndYear(String brand, String model, int year);

}
