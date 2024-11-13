package com.main.Controller;



import com.main.entity.User;
import com.main.entity.UserProfileUpdateRequest;
import com.main.entity.UserRequest;
import com.main.service.UserService;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes, Model model) {

        if(userService.userRequestExists(user.getVoterCardNo())){
            model.addAttribute("error","You already applied for registration please wait.");
            return "signup";
        }

        if (userService.userExists(user.getVoterCardNo())) {
            model.addAttribute("error", "User already registered with this Voter Card number.");
            return "signup";
        }


        UserRequest userRequest = new UserRequest();
        userRequest.setVoterCardNo(user.getVoterCardNo());
        userRequest.setUsername(user.getName());
        userRequest.setGender(user.getGender());
        userRequest.setDob(user.getDob());
        userRequest.setMobile(user.getMobile());
        userRequest.setPassword(passwordEncoder.encode(user.getPassword()));

        userRequest.setApproved(false);

        userService.saveUserRequest(userRequest);

        redirectAttributes.addFlashAttribute("message", "Registration Request Sent for Approval.");
        return "redirect:/welcome";
    }

    @PostMapping("/reset")
    public String ResetDetails(@ModelAttribute User user, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        User currentUser = userService.findByVoterCardNo(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
            redirectAttributes.addFlashAttribute("Message", "Wrong password. Please try again.");
            return "redirect:/SeeProfile"; // Ensure this path returns the profile page
        }


        UserProfileUpdateRequest updateRequest = new UserProfileUpdateRequest();
        updateRequest.setVoterCardNo(user.getVoterCardNo());
        updateRequest.setUsername(user.getName());
        updateRequest.setNewGender(user.getGender());
        updateRequest.setNewDob(user.getDob());
        updateRequest.setNewMobile(user.getMobile());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            updateRequest.setNewPassword(passwordEncoder.encode(user.getPassword()));
        }

        updateRequest.setApproved(false);
        userService.saveUserProfileUpdateRequest(updateRequest);

        // Add a success message for the user
        redirectAttributes.addFlashAttribute("message", "Updation Request Sent for Approval.");
        return "redirect:/welcome";
    }


    @RequestMapping("/signup")
    public String registerPage() {
        return "login";
    }


    @PostMapping("/forgotPassword")
    public String handleForgotPassword(@RequestParam String voterCardNo, @RequestParam String mobile,
                                       @RequestParam String dob, @RequestParam String newPassword,
                                       RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userService.findByVoterCardNo(voterCardNo);
        logger.info("Input VoterCardNo: " + voterCardNo);
        logger.info("Input DOB: " + dob);
        logger.info("Input MobileNo: " + mobile);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();


            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate inputDobDate = LocalDate.parse(dob, formatter);

                if (user.getMobile().equals(mobile) && user.getDob().equals(inputDobDate)) {
                    // Encode and update the new password
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userService.updateUser(user);
                    redirectAttributes.addFlashAttribute("successMessage", "Password reset successfully. You can now log in.");
                    return "redirect:/loginpage";
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "Details do not match. Please try again.");
                }
            } catch (DateTimeParseException e) {
                logger.error("Error parsing DOB: " + e.getMessage());
                redirectAttributes.addFlashAttribute("errorMessage", "Invalid date format.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "User not found.");
        }
        return "redirect:/forgot";
    }

}