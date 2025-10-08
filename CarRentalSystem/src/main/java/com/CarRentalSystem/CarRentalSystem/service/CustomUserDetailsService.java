package com.CarRentalSystem.CarRentalSystem.service;


import com.CarRentalSystem.CarRentalSystem.model.UserEntity;
import com.CarRentalSystem.CarRentalSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(user.getUsername())
                .password(user.getPassword()) // already encoded
                .roles(user.getRole().replace("ROLE_", "")) // convert "ROLE_ADMIN" -> "ADMIN"
                .build();
    }
}
