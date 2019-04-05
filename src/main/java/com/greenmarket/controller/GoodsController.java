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
import com.greenmarket.entity.Good;
import com.greenmarket.service.IGoodService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	private IGoodService goodService;
	@Autowired
	private IUserDao userService;
	
	
	
	@RequestMapping(value="/get/{id}")
	public String getById(Model model,@PathVariable(value="id") int id)
	{
		Good goods= goodService.getByid(id);
		model.addAttribute("goods",goods);
		return "GoodsInfo";
	}
	@RequestMapping(value="/getbyuser")
	public String getAllGoodsByUser(Model model,Authentication authentication)
	{
		String user = authentication.getName();
		int userid = userService.getUserByName(user).getId();
		System.out.println(user);
		System.out.println(userid);
		List<Good> lstGood = goodService.getAllByUser(userid);
		model.addAttribute("lstGoods",lstGood);
		return "ListByUser";
	}
	@RequestMapping("/create")
	public String addGoods(Model model)
	{
		Good newGoods = new Good();
		model.addAttribute("Goods",newGoods);
		return "AddGoods";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String addGoods(Model model,@ModelAttribute("Goods") Good good)
	{
		goodService.insert(good);
		return "redirect:/all";
	}
	@RequestMapping(value="/edit/{id}")
	public String editGoods(Model model,@PathVariable(value="id") int id)
	{
		
		Good good=goodService.getByid(id);
		model.addAttribute("GoodsEdit",good);
		return "redirect:/all";
	}
	@RequestMapping(value="/edit")
	public String editGoods(Model model,@ModelAttribute("farmEdit") Good good)
	{
		goodService.update(good);
		return "redirect:/all";
	}
	@RequestMapping("/all")
	public String listGoods(Model model)
	{	
		List<Good> lstGood =goodService.getAll();
		model.addAttribute("lstFarm",lstGood);
		return "ListByUser";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteGoods(Model model,@PathVariable(value="id") int id)
	{
		goodService.delete(id);
		return "redirect:/all";
	}
}
