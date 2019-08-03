package com.risk.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.risk.model.User;
import com.risk.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;

	User g_userdetails;

	// LogIn Page(Author=Nupur)
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("user", new User());
		return "Login";
	}

	// Registration Page(Author=Nupur)
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String show1(Model model) {
		model.addAttribute("user1", new User());
		return "Register";
	}

	// Registration Page Validation(Author=Nupur)
	@RequestMapping(value = "/RegisterSucess", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user1") User user, BindingResult result, Map<String, Object> map,
			ModelMap mapvalidate, HttpServletRequest request) {
		User userResult = new User();
		if (userservice.validateuserName(user.getUserName())) {
			mapvalidate.put("user", user);
			mapvalidate.addAttribute("invalidUserName", "User Name already exist");
			return "Register";
		}

		if (userservice.validateAdharNum(user.getUserAdharNum())) {
			mapvalidate.put("user", user);
			mapvalidate.addAttribute("invalidAdhar", "Adhar number already exist");
			return "Register";
		}
		if (userservice.validateuserPhoneNum(user.getUserPhoneNum())) {
			mapvalidate.put("user", user);
			mapvalidate.addAttribute("invalidPhoneNum", "Phone Number already exist");
			return "Register";
		}

		if (userservice.validateuserEmailId(user.getUserEmailId())) {
			mapvalidate.put("user", user);
			mapvalidate.addAttribute("invalidEmailId", "EmailId already exist");
			return "Register";
		}

		if (result.hasErrors()) {
			return "Register";
		}
		userservice.add(user);
		userResult = user;

		request.getSession().setAttribute("userName", user.getUserName());
		request.getSession().setAttribute("userEmailId", user.getUserEmailId());
		request.getSession().setAttribute("userFullName", user.getUserFullName());
		request.getSession().setAttribute("userPhoneNum", user.getUserPhoneNum());

		map.put("user", userResult);
		return "home";
	}

	// Login Page Validation for Valid User(Author=Nupur)
	@RequestMapping("/validateLogin")
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap map,
			HttpServletRequest request) {
		String userName = user.getUserName();
		if (userName.equals("admin")) {
			boolean adminCredentials = userservice.checkLogin(user.getUserName(), user.getUserPassword());
			if (adminCredentials) {
				request.getSession().setAttribute("admin", "logged in");
				request.getSession().setAttribute("loggedOut", null);
				return "redirect:/hotel";
			} else {
				return "redirect:/invaliduser";
			}
		}
		boolean userExists = userservice.checkLogin(user.getUserName(), user.getUserPassword());

		if (userExists) {
			map.put("user", user);
			map.addAttribute("invaliadUserMessage", "");
			g_userdetails = userservice.getUserDetails(user.getUserName());
			
			request.getSession().setAttribute("loggedIn", true);
			request.getSession().setAttribute("loggedOut", null);
			request.getSession().setAttribute("userName", g_userdetails.getUserName());
			request.getSession().setAttribute("userEmailId", g_userdetails.getUserEmailId());
			request.getSession().setAttribute("userFullName", g_userdetails.getUserFullName());
			request.getSession().setAttribute("userPhoneNum", g_userdetails.getUserPhoneNum());
			
			if(request.getSession().getAttribute("requestLogin") != null) {
				request.getSession().setAttribute("requestLogin", false);
				return "Payment";
			}
			return "home";
		} else {
			return "redirect:/invaliduser";
		}
	}

	// Login Page Validation for Invalid User(Author=Nupur)
	@RequestMapping("/invaliduser")
	public String invaliduser(@ModelAttribute("user") User user, ModelMap map) {
		map.addAttribute("invaliadUserMessage", "Invalid UserName or Password");
		return "Login";
	}
	
	//Controller to logout from current session
	@RequestMapping("/logout")
	public String logout(HttpServletRequest session) {
		session.getSession().setAttribute("loggedOut", "logged out");
		return "redirect:/home";
	}

}
