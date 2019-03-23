package com.greenmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenmarket.entity.Farm;
import com.greenmarket.service.IFarmService;

@Controller("/farm")
public class FarmController {
	@Autowired
	private IFarmService _farmService;
	
	
	@RequestMapping("/create")
	public String getAllFarmByUser(Model model)
	{
		int userId = 1;
		List<Farm> lstFarm = _farmService.getAllByUserId(userId);
		model.addAttribute("lstFarm",lstFarm);
		return "Farm";
	}
	

}
