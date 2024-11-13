package com.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.Repository.CandidateRepository;
import com.main.Repository.ElectionRepository;
import com.main.entity.Candidate;
import com.main.entity.Election;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private CandidateRepository candidateRepository; 

    public Election saveElection(Election election) {
        return electionRepository.save(election);
    }

    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public Election getElectionById(Long id) {
        Optional<Election> optionalElection = electionRepository.findById(id);
        return optionalElection.orElse(null);
    }


    public List<Candidate> getCandidatesByElection(Election election) {
        return candidateRepository.findByElection(election); 
    }

    public void deleteCandidatesByElection(Election election) {
        List<Candidate> candidates = candidateRepository.findByElection(election);
        candidateRepository.deleteAll(candidates);
    }
    
    
    @Transactional
    public void declareElectionResult(Long electionId) {
        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid election Id:" + electionId));

      
        election.setResultDeclared(true);
        electionRepository.save(election);
    }

    

    public List<Election> getElectionsForVoting() {
        
        return electionRepository.findByResultDeclaredFalse();
    }
    public List<Election> getDeclaredElections() {
        return electionRepository.findByResultDeclared(true);
    }

}
