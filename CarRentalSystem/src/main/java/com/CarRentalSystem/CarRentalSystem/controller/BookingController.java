package com.CarRentalSystem.CarRentalSystem.controller;

import com.CarRentalSystem.CarRentalSystem.dto.BookingResponseDTO;
import com.CarRentalSystem.CarRentalSystem.model.BookingEntity;
import com.CarRentalSystem.CarRentalSystem.model.CarEntity;
import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import com.CarRentalSystem.CarRentalSystem.repositories.BookingRepository;
import com.CarRentalSystem.CarRentalSystem.repositories.CarRepository;
import com.CarRentalSystem.CarRentalSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired private BookingRepository bookingRepo;
    @Autowired private CarRepository carRepo;
    @Autowired private UserRepository userRepo;

    // Book a car
    @PostMapping
    public BookingResponseDTO bookCar(@RequestBody BookingEntity bookingRequest, Authentication auth) {
        UserEntity user = userRepo.findByUsername(auth.getName()).orElseThrow();
        CarEntity car = carRepo.findById(bookingRequest.getCar().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Car not found with ID: " + bookingRequest.getCar().getId()
                ));



        long days = ChronoUnit.DAYS.between(bookingRequest.getRentalStartDate(), bookingRequest.getRentalEndDate());
        double totalCost = days * car.getPricePerDay();

        bookingRequest.setUser(user);
        bookingRequest.setCar(car);
        bookingRequest.setTotalCost(totalCost);
        bookingRequest.setStatus("BOOKED");

        BookingEntity savedBooking = bookingRepo.save(bookingRequest);

        return convertToDTO(savedBooking);
    }

    // View all my bookings (both active + cancelled)
    @GetMapping("/me")
    public List<BookingResponseDTO> myBookings(Authentication auth) {
        UserEntity user = userRepo.findByUsername(auth.getName()).orElseThrow();
        return bookingRepo.findByUser(user)
                .stream()
                .filter(booking -> !"CANCELLED".equalsIgnoreCase(booking.getStatus())) // skip cancelled
                .map(this::convertToDTO)
                .toList();
    }

    // Cancel a booking (changes status instead of deleting)

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        return bookingRepo.findById(id)
                .map(booking -> {
                    // Scenario 2: ALREADY CANCELLED
                    if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
                        return ResponseEntity.ok(" Booking with ID " + id + " is already cancelled.");
                    }

                    // Scenario 3: SUCCESSFUL CANCELLATION
                    booking.setStatus("CANCELLED");
                    bookingRepo.save(booking);
                    return ResponseEntity.ok("✅ Booking cancelled successfully for ID: " + id);
                })
                .orElse(
                        // Scenario 1: NOT FOUND
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND) // Returns 404
                                .body("Booking with ID " + id + " not found.")
                );
    }

    // View only active bookings
    @GetMapping("/active")
    public List<BookingResponseDTO> getActiveBookings(Authentication auth) {
        UserEntity user = userRepo.findByUsername(auth.getName()).orElseThrow();
        List<BookingEntity> bookings = bookingRepo.findByUserAndStatusNot(user, "CANCELLED");

        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // View booking history (cancelled or completed)
    @GetMapping("/history")
    public List<BookingResponseDTO> getBookingHistory(Authentication auth) {
        UserEntity user = userRepo.findByUsername(auth.getName()).orElseThrow();
        List<String> historyStatuses = List.of("CANCELLED", "COMPLETED");

        List<BookingEntity> bookings = bookingRepo.findByUserAndStatusIn(user, historyStatuses);

        return bookings.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method
    private BookingResponseDTO convertToDTO(BookingEntity booking) {
        return new BookingResponseDTO(
                booking.getId(),
                booking.getUser().getUsername(),
                booking.getCar().getBrand() + " " + booking.getCar().getModel(),
                booking.getCar().getFuelType(),
                booking.getCar().getPricePerDay(),
                booking.getCar().getYear(),
                booking.getRentalStartDate(),
                booking.getRentalEndDate(),
                booking.getTotalCost(),
                booking.getStatus(),
                booking.getPickupLocation(),
                booking.getDropoffLocation()
        );
    }



    // Admin: View all bookings
    @GetMapping("/all")
    public List<BookingResponseDTO> allBookings() {
        return bookingRepo.findAll().stream()
                .map(b -> new BookingResponseDTO(
                        b.getId(),
                        b.getUser().getUsername(),
                        b.getCar().getBrand() + " " + b.getCar().getModel(),
                        b.getCar().getFuelType(),
                        b.getCar().getPricePerDay(),
                        b.getCar().getYear(),
                        b.getRentalStartDate(),
                        b.getRentalEndDate(),
                        b.getTotalCost(),
                        b.getStatus(),
                        b.getPickupLocation(),
                        b.getDropoffLocation()
                ))
                .toList();
    }



    // Admin: View all currently active bookings across all users
    @GetMapping("/active/all")
    public List<BookingResponseDTO> allActiveBookings() {
        return bookingRepo.findByStatus("BOOKED").stream()
                .map(this::convertToDTO) // Reuses the existing helper method
                .toList();
    }
}
