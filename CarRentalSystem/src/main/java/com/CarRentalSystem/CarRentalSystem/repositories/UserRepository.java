package com.CarRentalSystem.CarRentalSystem.repositories;


import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
