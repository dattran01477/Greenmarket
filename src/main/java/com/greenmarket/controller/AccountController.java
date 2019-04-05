package com.greenmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenmarket.dao.IUserDao;
import com.greenmarket.entity.User;

@Controller
public class AccountController {

	@Autowired
	private IUserDao _userService;
	
	public AccountController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value="/register")
	public String register(Model model)
	{
		model.addAttribute("refix", new User());
		return "Register";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(Model model,@ModelAttribute(value="refix") User user)
	{
			return "Register";
	}
	
	@RequestMapping(value="/login")
	public String login(Model model)
	{
		model.addAttribute("refix", new User());
		return "Login";
	}

	@RequestMapping(value="/profile")
	public String login(Model model, Authentication authentication)
	{
		String username = authentication.getName();
		User user = _userService.getUserByName(username);
		
		model.addAttribute("user",user);
		return "Profile";
	}

	
}
