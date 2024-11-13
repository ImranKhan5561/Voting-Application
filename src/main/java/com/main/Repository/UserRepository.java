package com.main.Repository;



import com.main.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByVoterCardNo(String voterCardNo);

	boolean existsByVoterCardNo(String voterCardNo);
	Optional<User> findByVoterCardNoAndMobile(String voterCardNo, String mobileNumber);
}
