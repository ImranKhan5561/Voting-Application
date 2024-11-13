package com.main.Repository;

import com.main.entity.UserProfileUpdateRequest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileUpdateRequestRepo extends JpaRepository<UserProfileUpdateRequest, Long> {
    List<UserProfileUpdateRequest> findByIsApprovedFalse();

    boolean existsByVoterCardNo(String voterCardNo);
}
