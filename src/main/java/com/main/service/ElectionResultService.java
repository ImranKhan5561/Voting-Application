package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Repository.CandidateRepository;
import com.main.Repository.CandidateResultRepository;
import com.main.Repository.ElectionResultRepository;
import com.main.Repository.VoteRepository;
import com.main.entity.Candidate;
import com.main.entity.CandidateResult;
import com.main.entity.Election;
import com.main.entity.ElectionResult;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElectionResultService {

    @Autowired
    private ElectionResultRepository electionResultRepository;

    @Autowired
    private CandidateResultRepository candidateResultRepository;
    
    @Autowired
    private VoteRepository voteRepository;
    
    
    @Autowired
    private CandidateRepository candidateRepository;
    public ElectionResult declareResult(Election election, List<Candidate> candidates) {
        ElectionResult electionResult = new ElectionResult();
        electionResult.setElection(election);
        electionResult.setResultDeclaredDate(LocalDateTime.now());
        
        electionResultRepository.save(electionResult);

        List<Object[]> voteCounts = voteRepository.countVotesByElection(election.getId());

        for (Object[] voteCount : voteCounts) {
            Long candidateId = (Long) voteCount[0];
            Long count = (Long) voteCount[1];

           
            Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found"));

            
            CandidateResult candidateResult = new CandidateResult();
            candidateResult.setCandidate(candidate);
            candidateResult.setVoteCount(count.intValue());
            candidateResult.setElectionResult(electionResult);

            candidateResultRepository.save(candidateResult);
        }
        return electionResult;
    }

    public List<CandidateResult> getResultsForElection(Election election) {
        ElectionResult electionResult = electionResultRepository.findByElection(election);
        return candidateResultRepository.findByElectionResultOrderByVoteCountDesc(electionResult);
    }
   
       
    }


