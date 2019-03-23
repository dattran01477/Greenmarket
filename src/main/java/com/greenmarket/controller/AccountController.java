package com.greenmarket.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenmarket.entity.Farm;
import com.greenmarket.entity.User;

@Controller
public class AccountController {

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

	
}
