package com.greenmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenmarket.entity.Farm;
import com.greenmarket.service.IFarmService;

@Controller("/farm")
public class FarmController {
	@Autowired
	private IFarmService farmService;
	
	
	@RequestMapping("/create")
	public String getAllFarmByUser(Model model)
	{
		
		
		model.addAttribute("a",model);
		return "Farm";
	}
	

}
