package com.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Election;
import com.main.entity.ElectionResult;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
    ElectionResult findByElection(Election election);
}
