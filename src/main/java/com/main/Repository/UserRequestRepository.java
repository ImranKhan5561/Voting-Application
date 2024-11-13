package com.main.Repository;

import com.main.entity.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {


    List<UserRequest> findByIsApprovedFalse();

    boolean existsByVoterCardNo(String voterCardNo);
}
