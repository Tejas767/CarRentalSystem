package com.CarRentalSystem.CarRentalSystem.repositories;

import com.CarRentalSystem.CarRentalSystem.model.BookingEntity;
import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    // Existing
    List<BookingEntity> findByUser(UserEntity user);

    // For filtering active bookings (anything not CANCELLED)
    List<BookingEntity> findByUserAndStatusNot(UserEntity user, String status);

    // For filtering history (CANCELLED or COMPLETED)
    List<BookingEntity> findByUserAndStatusIn(UserEntity user, List<String> statuses);
    List<BookingEntity> findByStatus(String status);
}
