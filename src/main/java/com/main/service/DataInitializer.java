package com.main.service;


import com.main.entity.User;
import com.main.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByVoterCardNo("123456789012").isEmpty()) {
                User admin = new User();
                admin.setVoterCardNo("123456789012");
                admin.setName("Admin User");
                
                admin.setPassword(passwordEncoder.encode("admin_password"));
                admin.setGender("Male");
                admin.setMobile("9876543210");
                admin.setDob(LocalDate.of(1980, 1, 1));
                admin.setRoles(Set.of("ADMIN"));

                userRepository.save(admin);
            }
        };
    }
}
