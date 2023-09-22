package com.getinfy.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.getinfy.dto.Loginform;
import com.getinfy.dto.SignUpform;
import com.getinfy.dto.Unlockform;
import com.getinfy.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userservice;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/")
	public String loadform(Model model) {
		 logger.info("Loading registration form");

		model.addAttribute("user", new SignUpform());
		return "index";
	}

	@PostMapping("/saveEmployee")
	public String Registration(@Valid @ModelAttribute("user") SignUpform signUpform, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			logger.error("Validation errors in registration form");
			return "index";
		}

		boolean userSignup = userservice.UserSignup(signUpform);
		if (userSignup) {
			logger.info("User registered successfully: ", signUpform.getStaffEmail());
			model.addAttribute("save", "Details saved successfully..");
		}else {
			logger.warn("User registration failed, email already exists: ", signUpform.getStaffEmail());
			model.addAttribute("errMsg", "please use different email ");
		}

		return "index";

	}

	@GetMapping("/unlock")
	public String showUnlockForm(Model model) {
		logger.info("Loading unlock account form");
		model.addAttribute("unlockform", new Unlockform());

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockAccount(@Valid @ModelAttribute("unlockform") Unlockform unlockform, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "unlock";
		}
		if(unlockform.getConfirmPwd().equals(unlockform.getNewPwd())) {
			boolean isUnlockSuccessful = userservice.Unlock(unlockform);

			if (isUnlockSuccessful) {
				model.addAttribute("unlockSuccess", "your account is unlocked ");
			}else {
				model.addAttribute("unlock", "please check your credentials");
			}
			
			
		}else {
			model.addAttribute("errormsg", "Invalid credentials");
		}
		
		
		return "unlock";

	}

	@GetMapping("/login")
	public String showloginForm(Model model) {
		 logger.info("Loading login form");

		model.addAttribute("loginform", new Loginform());

		return "login";
	}

	@PostMapping("/loginlogic")
	public String loginlogic(@Valid @ModelAttribute("loginform") Loginform form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "login";
		}

		String status = userservice.login(form);

		if (status.contains("Success")) {

			return "redirect:/dashboardPage";

		}

		model.addAttribute("errMsg", status);

		return "login";
	}

	@GetMapping("/forgot")
	public String forgot(Model model) {
		logger.info("Loading forgot password form");
		return "forgot";
	}

	@PostMapping("/forgotlogic")
	public String forgotlogic(@RequestParam("email") String email, Model model) {

		boolean status = userservice.forgot(email);

		if (status) {
			model.addAttribute("succmsg", "password sent to your Email");

		} else {
			model.addAttribute("errmsg", "Email not found");
		}

		return "forgot";
	}

}
