package com.main.Controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.main.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.main.entity.Candidate;
import com.main.entity.CandidateResult;
import com.main.entity.Election;
import com.main.entity.User;


@Controller
public class HomeController {

	@Autowired
	private ElectionService electionService;

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private UserService userService;
	@Autowired
	private ElectionResultService electionResultService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private AuthenticationManager authenticationManager;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@RequestMapping("/welcome")
	public String welcome(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = "Guest";

		if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
			Optional<User> user = userService.findByVoterCardNo(authentication.getName());
			if (user.isPresent()) {
				username = user.get().getName();
			}
		}

		model.addAttribute("username", username);
		model.addAttribute("elections", electionService.getElectionsForVoting());


		List<Election> declaredElections = electionService.getDeclaredElections();
		model.addAttribute("declaredElections", declaredElections);

		return "welcome";
	}

	@GetMapping("/loginpage")
	public String loginPage() {

		return "login";
	}

	@GetMapping("/loginpage1")
	public String loginPage1(@RequestParam(value = "error", required = false) String error,
							 @RequestParam(value = "voterCardNo", required = false) String voterId,
							 Model model) {
		// Log the incoming values of error and voterId
		logger.debug("Received login request with error: {} and voterCardNo: {}", error, voterId);

		if (error != null) {
			if (voterId != null && userService.userRequestExists(voterId)) {
				logger.info("User request exists for voterCardNo: {}", voterId);
				model.addAttribute("message", "Your request approval is pending, please wait.");
			} else {
				logger.info("Invalid username or password for voterCardNo: {}", voterId);
				model.addAttribute("message", "Invalid username or password. Please try again.");
			}
		}
		return "login";  // Return the logical view name
	}



	@GetMapping("/signuppage")
	public String signuppage() {
		return "signup";
	}

	@GetMapping("/voting")
	@PreAuthorize("hasRole('USER')")
	public String votingPage(@RequestParam("electionId") Long electionId, Model model) {
		Election election = electionService.getElectionById(electionId);
		List<Candidate> candidates = candidateService.getCandidatesByElection(election);
		model.addAttribute("candidates", candidates);
		model.addAttribute("election", election);
		return "voting";
	}


	@GetMapping("/access-denied")
	public String AccessHandler() {
		return "access-denied";
	}

	@GetMapping("/error")
	public String StringErrorHandler() {
		return "access-denied";
	}

	@GetMapping("/seeResults")
	public String seeResults(@RequestParam("electionId") Long electionId, Model model) {

		System.out.println("Received electionId: " + electionId);


		Election election = electionService.getElectionById(electionId);
		System.out.println("Retrieved election: " + election);


		if (election == null) {
			System.out.println("Election not found for ID: " + electionId);
			model.addAttribute("error", "Election not found");
			return "error";
		}


		List<CandidateResult> results = electionResultService.getResultsForElection(election);
		System.out.println("Retrieved results: " + results);


		if (results.isEmpty()) {
			System.out.println("No results found for election ID: " + electionId);
			model.addAttribute("error", "No results found for this election");
			return "error";
		}


		model.addAttribute("election", election);
		model.addAttribute("results", results);


		return "results";
	}


	@GetMapping("/live-results")
	public String getLiveResults(@RequestParam("electionId") Long electionId, Model model) {
		List<Object[]> results = voteService.getLiveResults(electionId);
		String electionName = results.isEmpty() ? "No Data to Present" : (String) results.get(0)[3];
		model.addAttribute("results", results);
		model.addAttribute("electionName", electionName);
		return "liveresults";
	}

	@GetMapping("/SeeProfile")
	public String seeProfile(Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    String username = "Guest";
	    String voterId = "1235454738";
	    String gender = "Male";
	    String mobile = "9876543210";
	    String password = "*********";
	    LocalDate dob = LocalDate.parse("2004-08-15");

	    // Check if the user is authenticated
	    if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
	        Optional<User> user = userService.findByVoterCardNo(authentication.getName());
	        if (user.isPresent()) {
	            username = user.get().getName();
	            voterId = user.get().getVoterCardNo();
	            gender = user.get().getGender();
	            mobile = user.get().getMobile();
	            password = user.get().getPassword();
	            dob = user.get().getDob();

	            // Fetch the user's voting history
	            List<Object[]> votingHistory = voteService.getVotingHistory(voterId);
	            model.addAttribute("votingHistory", votingHistory);
	        }
	    }

	    // Add profile details to the model
	    model.addAttribute("username", username);
	    model.addAttribute("voterId", voterId);
	    model.addAttribute("gender", gender);
	    model.addAttribute("mobile", mobile);
	    model.addAttribute("password", password);
	    model.addAttribute("dob", dob);

	    return "SeeProfile";
	}


@GetMapping("/forgot")
	public String forgot(){
		return "forgot";
}


}


	

