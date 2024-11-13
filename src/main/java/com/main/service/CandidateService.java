package com.main.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Repository.CandidateRepository;
import com.main.entity.Candidate;
import com.main.entity.Election;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public List<Candidate> getCandidatesByElection(Election election) {
        return candidateRepository.findByElection(election);
    }

	
}
