package com.main.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Candidate;
import com.main.entity.Election;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByElection(Election election);
}
