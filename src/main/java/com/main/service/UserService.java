package com.main.service;
import com.main.Repository.UserProfileUpdateRequestRepo;
import com.main.Repository.UserRequestRepository;
import com.main.entity.User;
import com.main.Repository.UserRepository;

import java.util.*;

import com.main.entity.UserProfileUpdateRequest;
import com.main.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private UserProfileUpdateRequestRepo Pu;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean userExists(String voterCardNo) {
        return userRepository.existsByVoterCardNo(voterCardNo);
    }

    public boolean userRequestExists(String voterCardNo) {
    	return userRequestRepository.existsByVoterCardNo(voterCardNo);
    }
    
    public void saveUserRequest(UserRequest userRequest) {
        userRequestRepository.save(userRequest);
    }

    public void saveUserProfileUpdateRequest(UserProfileUpdateRequest u){Pu.save(u);}
    public List<UserRequest> getAllPendingRequests() {
        return userRequestRepository.findByIsApprovedFalse(); // Fetch only pending requests
    }
    public List<Map<String, Object>> getAllUpdateRequests() {
        List<UserProfileUpdateRequest> updateRequests = Pu.findByIsApprovedFalse();
        List<Map<String, Object>> combinedRequests = new ArrayList<>();

        for (UserProfileUpdateRequest request : updateRequests) {
            User currentUser = userRepository.findByVoterCardNo(request.getVoterCardNo())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Store both current user data and update request in a map
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("currentUser", currentUser);
            requestData.put("updateRequest", request);

            combinedRequests.add(requestData);
        }

        return combinedRequests;
    }

    public Optional<User> findByVoterCardNo(String voterCardNo) {
		
		return userRepository.findByVoterCardNo(voterCardNo);
	}
    public void approveUserRequest(Long requestId) {
        UserRequest userRequest = userRequestRepository.findById(requestId).orElseThrow();


        User user = new User();
        user.setVoterCardNo(userRequest.getVoterCardNo());
        user.setName(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setDob(userRequest.getDob());
        user.setGender(userRequest.getGender());
        user.setMobile(userRequest.getMobile());
        user.setRoles(Set.of("USER"));

        userRepository.save(user);

        // Remove the request after approval
        userRequestRepository.delete(userRequest);
    }
    public void RejectUserRequest(Long requestId) {

       

        userRequestRepository.deleteById(requestId);
    }
    public void approveUserURequest(Long requestId) {
        // Find the profile update request by ID
        UserProfileUpdateRequest userRequest = Pu.findById(requestId).orElseThrow(() ->
                new IllegalArgumentException("Request not found")
        );

        // Retrieve the existing user by voterCardNo or another unique identifier
        User user = userRepository.findByVoterCardNo(userRequest.getVoterCardNo())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Update the user details with the new information from the request
        if (userRequest.getNewMobile() != null) {
            user.setMobile(userRequest.getNewMobile());
        }
        if (userRequest.getUsername() != null) {
            user.setName(userRequest.getUsername());
        }
        if (userRequest.getNewGender() != null) {
            user.setGender(userRequest.getNewGender());
        }
        if (userRequest.getNewDob() != null) {
            user.setDob(userRequest.getNewDob());
        }
        if (userRequest.getNewPassword() != null) {

            user.setPassword(userRequest.getNewPassword());
        }
        userRepository.save(user);

        Pu.delete(userRequest);
    }

    public void RejectUserURequest(Long requestId) {



        Pu.deleteById(requestId);
    }
    public void updateUser(User updatedUser) {
        User existingUser = userRepository.findByVoterCardNo(updatedUser.getVoterCardNo()).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(updatedUser.getName());
        existingUser.setDob(updatedUser.getDob());
        existingUser.setGender(updatedUser.getGender());

        userRepository.save(existingUser);
    }


}
