package com.greenmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenmarket.entity.Farm;
import com.greenmarket.service.IFarmService;

@Controller
@RequestMapping("/farm")
public class FarmController {
	@Autowired
	private IFarmService _farmService;
	
	
	@RequestMapping(value="/get")
	public String getAllFarmByUser(Model model)
	{
		int userId = 1;
		List<Farm> lstFarm = _farmService.getAllByUserId(userId);
		model.addAttribute("lstFarm",lstFarm);
		return "Farm";
	}
	@RequestMapping("/create")
	public String addFarm(Model model)
	{
		Farm newFarm = new Farm();
		model.addAttribute("farm",newFarm);
		return "AddFarm";
	}
	@RequestMapping("/edit")
	public String editFarm(Model model)
	{
		int farmId = 1;
		Farm farmEdit = _farmService.getByid(farmId);
		model.addAttribute("farmEdit",farmEdit);
		return "EditFarm";
	}
	@RequestMapping("/all")
	public String listFarm(Model model)
	{
		List<Farm> lstFarm = _farmService.getAll();
		model.addAttribute("lstFarm",lstFarm);
		return "ListFarm";
	}
	
	@RequestMapping("/delete")
	public String deleteFarm(Model model)
	{
		int farmId = 1;
		_farmService.delete(farmId);	
		return "ListFar";
	}

}
