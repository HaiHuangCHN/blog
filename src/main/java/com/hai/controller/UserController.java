package com.hai.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hai.entity.User;
import com.hai.request.LoginReq;
import com.hai.service.UserService;
import com.hai.utils.MD5Util;

@Controller
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);
	private static final String LOGGED_IN = "logged_in";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupPage(HttpServletRequest request) {
		logger.info(request.getRequestURL().toString());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		logger.info(request.getRequestURL().toString());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String encodeUsername = MD5Util.uppercaseMD5(username);
		String encodePassword = MD5Util.uppercaseMD5(password);
		User user = new User(encodeUsername, encodePassword);
		user.setCreatedDate(timestamp);
		user.setUpdatedDate(timestamp);
		userService.save(user);
		return "redirect:/login";
	}

	/**
	 * Login
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request, HttpSession session, LoginReq loginReq) {
		logger.info(request.getRequestURL().toString() + ", method: " + request.getMethod() + loginReq.toString());

		if (session.getAttribute(LOGGED_IN) != null) {
			return "redirect:/";
		}
		return "login";
	}

	@PostMapping(value = "/login"/* , produces = {"application/*"} */)
	public String loginNew(HttpServletRequest request, HttpSession session, @Valid LoginReq loginReq,
			BindingResult bindingResult) {
		logger.info(request.getRequestURL().toString());
		logger.info(loginReq.getUsername() + loginReq.getPassword());

		if (bindingResult.hasErrors()) {
			return "login";
		}

		if (session.getAttribute(LOGGED_IN) != null) {
			return "redirect:/";
		}

		if (!verify(loginReq.getUsername(), loginReq.getPassword())) {
			return "login";
		}

		session.setAttribute(LOGGED_IN, true);
		return "redirect:/";
	}

	/**
	 * Verify username & password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private boolean verify(String username, String password) {
		Boolean isSuccessful = false;
		String encodedUsername = MD5Util.uppercaseMD5(username);
		String encodedPassword = MD5Util.uppercaseMD5(password);
		User user = userService.findByUsernameAndPassword(encodedUsername, encodedPassword);
		if (user != null) {
			isSuccessful = true;
		}
		logger.info("Login result: " + isSuccessful);
		return isSuccessful;
	}

	/**
	 * Logout
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest request, HttpSession session) {
		logger.info(request.getRequestURL().toString());

		session.removeAttribute(LOGGED_IN);
		return "redirect:/";
	}

}
