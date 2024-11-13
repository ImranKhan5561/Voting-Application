package com.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.CandidateResult;
import com.main.entity.ElectionResult;

public interface CandidateResultRepository extends JpaRepository<CandidateResult, Long> {
    List<CandidateResult> findByElectionResultOrderByVoteCountDesc(ElectionResult electionResult);
}
