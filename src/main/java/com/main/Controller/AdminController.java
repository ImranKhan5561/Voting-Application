package com.main.Controller;

   
    
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.Candidate;

import com.main.entity.Election;
import com.main.service.CandidateService;
import com.main.service.ElectionResultService;
import com.main.service.ElectionService;
import com.main.service.UserService;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ElectionService electionService;

    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private ElectionResultService electionResultService;
    
    @Autowired
    private UserService userservice;

    @GetMapping("/panel")
    @PreAuthorize("hasRole('ADMIN')")  
    public String adminPanel(Model model) {
        model.addAttribute("elections", electionService.getElectionsForVoting());
        model.addAttribute("pending",userservice.getAllPendingRequests());
        model.addAttribute("UpdatePending",userservice.getAllUpdateRequests());
        return "Admin";
    }

    @PostMapping("/addElection")
    @PreAuthorize("hasRole('ADMIN')")  
    public String addElection(@ModelAttribute Election election) {
        electionService.saveElection(election);
        return "redirect:/admin/panel";
    }

    @PostMapping("/addCandidate")
    @PreAuthorize("hasRole('ADMIN')")  
    public String addCandidate(@ModelAttribute Candidate candidate, 
                               @RequestParam("photoFile") MultipartFile photoFile,
                               @RequestParam("logoFile") MultipartFile logoFile,
                               @RequestParam("electionId") Long electionId) throws IOException {
        Election election = electionService.getElectionById(electionId);
        candidate.setElection(election);

        if (!photoFile.isEmpty()) {
            candidate.setPhoto(photoFile.getBytes());
        }

        if (!logoFile.isEmpty()) {
            candidate.setLogo(logoFile.getBytes());
        }

        candidateService.saveCandidate(candidate);
        return "redirect:/admin/panel";
    }

    @PostMapping("/deleteCandidatesByElection")
    @PreAuthorize("hasRole('ADMIN')")  
    public String deleteCandidatesByElection(@RequestParam("electionId") Long electionId) {
        Election election = electionService.getElectionById(electionId);
        electionService.deleteCandidatesByElection(election);
        return "redirect:/admin/panel";
        
    }   
    
    @GetMapping("/seeCandidates")
    @PreAuthorize("hasRole('ADMIN')")
    public String seeCandidates(@RequestParam("electionId") Long electionId, Model model) {
        // Fetch the election by its ID
        Election election = electionService.getElectionById(electionId);
        
        if (election == null) {
            
            model.addAttribute("error", "Election not found.");
            return "access-denied"; 
        }
        
        List<Candidate> candidates = election.getCandidates();       
        model.addAttribute("election", election);
        model.addAttribute("candidates", candidates);
        return "seeCandidates";
    }


    @PostMapping("/deleteCandidate")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCandidate(@RequestParam("candidateId") Long candidateId, @RequestParam("electionId") Long electionId) {
        candidateService.deleteCandidate(candidateId);
        return "redirect:/admin/seeCandidates?electionId=" + electionId;
    }

    

    @GetMapping("/updateCandidate/{candidateId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCandidate(@PathVariable Long candidateId, Model model) {
        Candidate candidate = candidateService.getCandidateById(candidateId);
        if (candidate == null) {
            return "redirect:/admin/seeCandidates?error=candidateNotFound";
        }
        model.addAttribute("candidate", candidate);
        model.addAttribute("elections", electionService.getElectionsForVoting());
        return "updateCandidate"; 
    }


    @PostMapping("/updateCandidate")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateCandidate(@ModelAttribute Candidate candidate, 
                                  @RequestParam("photoFile") MultipartFile photoFile,
                                  @RequestParam("logoFile") MultipartFile logoFile,
                                  @RequestParam("electionId") Long electionId) throws IOException {
        Election election = electionService.getElectionById(electionId);
        candidate.setElection(election);

        if (!photoFile.isEmpty()) {
            candidate.setPhoto(photoFile.getBytes());
        }

        if (!logoFile.isEmpty()) {
            candidate.setLogo(logoFile.getBytes());
        }

        candidateService.saveCandidate(candidate);
        return "redirect:/admin/panel";
    }
    
    @GetMapping("/declareResult")
    public String showDeclareResultPage(Model model) {
        model.addAttribute("elections", electionService.getElectionsForVoting());
        return "declareResult";
    }

    @PostMapping("/declareResult")
    @Transactional
    public String declareResult(@RequestParam("electionId") Long electionId, Model model) {
        Election election = electionService.getElectionById(electionId);
        List<Candidate> candidates = candidateService.getCandidatesByElection(election);

       
        electionResultService.declareResult(election, candidates);
        election.setResultDeclared(true);
        electionService.saveElection(election);  

        

        model.addAttribute("message", "Results declared successfully!");
        return "redirect:/admin/panel";
    }


    @PostMapping("/approve")
    public String approveUserRequest(@RequestParam Long requestId) {
        userservice.approveUserRequest(requestId);
        return "redirect:/admin/panel"; // Redirect back to requests page
    }
  
@PostMapping("/reject")
    public String rejectUserRequest(@RequestParam Long requestId){
        userservice.RejectUserRequest(requestId);
        return "redirect:/admin/panel";
}

    @PostMapping("/approveUpdate")
    public String approveUserURequest(@RequestParam Long requestId) {
        userservice.approveUserURequest(requestId);
        return "redirect:/admin/panel"; // Redirect back to requests page
    }

    @PostMapping("/rejectUpdate")
    public String rejectUserURequest(@RequestParam Long requestId){
        userservice.RejectUserURequest(requestId);
        return "redirect:/admin/panel";
    }


}


    


