package com.ty.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ty.webapp.config.EmailConfiguration;
import com.ty.webapp.entity.User;

@RestController
public class UserController {
	@Autowired
	private EmailConfiguration configuration;

	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("register.jsp");
		mv.addObject("user", new User());
		return mv;
	}

	@RequestMapping("/saveUser")
	public ModelAndView saveUser(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView("dashBoard.jsp");
		String loginInfo = "User Name = " + user.getUserEmail() + "\n Password :" + user.getUserPassword();
		if (configuration.sendEmail(user.getUserEmail(), "webonliebatches@gmail.com", "Login Credentials", loginInfo)) {
			mv.addObject("msg", "Mail Sent!!");
		} else {
			mv.addObject("msg", "Mail Not Sent!!");
		}

		return mv;
	}
}
