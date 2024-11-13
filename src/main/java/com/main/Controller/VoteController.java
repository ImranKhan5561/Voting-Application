package com.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.main.entity.User;
import com.main.entity.Vote;
import com.main.service.ElectionService;
import com.main.service.UserService;
import com.main.service.VoteService;


import java.security.Principal;
import java.util.Optional;
import com.main.entity.Election;




@Controller
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private ElectionService electionService;

    @PostMapping("/vote")
    public String vote(@ModelAttribute Vote vote, Principal principal, Model model) {
        String voterCardNo = principal.getName();

        
        Long electionId = vote.getElection().getId();
        Election election = electionService.getElectionById(electionId);

        if (election == null) {
            model.addAttribute("error", "Invalid election selected.");
            return "voting";
        }

        
        vote.setElection(election);
        Optional<User> optionalUser = userService.findByVoterCardNo(voterCardNo);
        boolean hasVoted = voteService.hasUserVotedInElection(voterCardNo, electionId);
        if (hasVoted) {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                model.addAttribute("user", user);
            }

            model.addAttribute("electionName", election.getName());
            model.addAttribute("electionDate", election.getDate());
            model.addAttribute("vote", vote);
            model.addAttribute("error", "You have already voted in this election. You cannot vote again.");
            return "certificate";
        }

        vote.setVoterCardNo(voterCardNo);
        voteService.saveVote(vote);


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
        }

        model.addAttribute("electionName", election.getName());
        model.addAttribute("electionDate", election.getDate());
        model.addAttribute("vote", vote);

        return "certificate";
    }

}
