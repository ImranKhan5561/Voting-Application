package com.main.service;


import com.main.entity.Vote;
import com.main.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    
    public boolean hasUserVotedInElection(String voterCardNo, Long electionId) {
        return voteRepository.existsByVoterCardNoAndElectionId(voterCardNo, electionId);
    }


    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    public List<Object[]> getLiveResults(Long electionId) {
        return voteRepository.getLiveResultsByElection(electionId);
    }
    
    public List<Object[]> getVotingHistory(String voterCardNo) {
        return voteRepository.getVotingHistoryByVoter(voterCardNo);
    }

}
