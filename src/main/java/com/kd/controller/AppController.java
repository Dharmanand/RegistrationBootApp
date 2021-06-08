package com.kd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kd.entiry.User;
import com.kd.repository.UserRepository;

@Controller
public class AppController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/index")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showSignUpPage(Model model) {
		model.addAttribute("user", new User());
		return "signUpForm";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return "registrationSuccess";
	}
	
	@GetMapping("/listUsers")
	public String listUsers(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
}
