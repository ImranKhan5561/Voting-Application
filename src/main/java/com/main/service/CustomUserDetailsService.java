package com.main.service;

import com.main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService; // Inject your existing UserService

    @Override
    public UserDetails loadUserByUsername(String voterCardNo) throws UsernameNotFoundException {
        User user = userService.findByVoterCardNo(voterCardNo)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with voter ID: " + voterCardNo));

        // Convert your User entity to a UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getVoterCardNo())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(String[]::new)) // Set roles if applicable
                .build();
    }
}
