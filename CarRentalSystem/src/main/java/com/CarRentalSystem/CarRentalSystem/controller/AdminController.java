package com.CarRentalSystem.CarRentalSystem.controller;




import com.CarRentalSystem.CarRentalSystem.model.BookingEntity;
import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import com.CarRentalSystem.CarRentalSystem.repositories.BookingRepository;
import com.CarRentalSystem.CarRentalSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired private UserRepository userRepo;
    @Autowired private BookingRepository bookingRepo;

    //  Get all users
    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    //  Delete a user
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "User with ID " + id + " deleted.";
    }

    //  Admin: Permanently delete a booking
    @DeleteMapping("/booking/{id}")
    public String deleteBooking(@PathVariable Long id) {
        if (!bookingRepo.existsById(id)) {
            // Returns a message if the booking ID doesn't exist
            return " Booking with ID " + id + " not found.";
        }

        // This performs the permanent deletion
        bookingRepo.deleteById(id);

        // Once deleted from the DB, it will automatically be removed from
        // all user lists (like /api/booking/me) on the next request.
        return "Booking with ID " + id + " permanently deleted.";
    }
    
}
