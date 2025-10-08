package com.CarRentalSystem.CarRentalSystem.controller;


import com.CarRentalSystem.CarRentalSystem.dto.PasswordUpdateRequest;
import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import com.CarRentalSystem.CarRentalSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("ROLE_USER"); // default role
        }
        userRepo.save(user);
        return "User registered successfully!";
    }




    @PutMapping("/update-password")
    public String updatePassword(@RequestBody PasswordUpdateRequest request) {

        UserEntity user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check old password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return "Old password is incorrect!";
        }

        // encode and save new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepo.save(user);

        return "Password updated successfully!";
    }

}


