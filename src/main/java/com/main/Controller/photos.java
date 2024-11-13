package com.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.entity.Candidate;
import com.main.service.CandidateService;
@Controller
public class photos {
	@Autowired
	CandidateService candidateService;
	 @GetMapping("/candidatePhoto/{id}")
	    @ResponseBody
	    public byte[] getCandidatePhoto(@PathVariable Long id) {
	        Candidate candidate = candidateService.getCandidateById(id);
	        return candidate.getPhoto();
	    }
	  
	    @GetMapping("/candidateLogo/{id}")
	    @ResponseBody
	    public byte[] getLogo(@PathVariable Long id) {
	        Candidate candidate = candidateService.getCandidateById(id);
	        return candidate.getLogo();
	    }
}
