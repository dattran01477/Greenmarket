package com.greenmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.greenmarket.dao.IUserDao;
import com.greenmarket.entity.Farm;
import com.greenmarket.entity.Good;
import com.greenmarket.service.ICategoryService;
import com.greenmarket.service.IFarmService;
import com.greenmarket.service.ILocationService;

@Controller
@RequestMapping("/farm")
public class FarmController {
	@Autowired
	private IFarmService _farmService;
	
	@Autowired
	private ICategoryService _categoryService;
	
	@Autowired
	private ILocationService _locationService;
	
	@Autowired
	private IUserDao userService;
	
	@RequestMapping(value="/getbyuser")
	public String getAllFarmByUser(Model model,Authentication authentication)
	{
		String user = authentication.getName();
		int userid = userService.getUserByName(user).getId();
		List<Farm> lstFarm=_farmService.getAllByUserId(userid);
		model.addAttribute("lstFarm",lstFarm);
		
		return "ListFarm";
	}
	
	@RequestMapping("/get/{id}")
	public String getById(Model model,@PathVariable(value="id") int id)
	{
		Farm farm= _farmService.getByid(id);
		model.addAttribute("farm",farm);
		return "FarmInfo";
	}
	
	@RequestMapping("/create")
	public String addFarm(Model model)
	{
		
		Farm newFarm = new Farm();
		model.addAttribute("farm",newFarm);
		model.addAttribute("lsCategory", _categoryService.getAll());
		model.addAttribute("lsProvince",_locationService.getAll());
		
		return "AddFarm";
	}
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String addFarm(Model model,@ModelAttribute("farm") Farm farm)
	{
		_farmService.insert(farm);
		return "redirect:/get";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editFarm(Model model,@PathVariable(value="id") int id)
	{
		Farm farm=_farmService.getByid(id);
		model.addAttribute("farmEdit",farm);
		return "EditFarm";
	}
	
	@RequestMapping(value="/edit")
	public String editFarm(Model model,@ModelAttribute("farmEdit") Farm farm)
	{
		_farmService.update(farm);
		return "redirect:/all";
	}
	
	@RequestMapping("/all")
	public String listFarm(Model model)
	{
		List<Farm> lstFarm =_farmService.getAll();
		model.addAttribute("lstFarm",lstFarm);
		return "ListFarm";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteFarm(Model model,@PathVariable(value="id") int id)
	{
		_farmService.delete(id);	
		return "redirect:/all";
	}
}