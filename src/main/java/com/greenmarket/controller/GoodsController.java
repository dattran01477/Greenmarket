package com.greenmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenmarket.entity.Good;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	
	@RequestMapping(value="/get")
	public String getAllGoodsByUser(Model model)
	{
		int userId = 1;
		model.addAttribute("lstGoods","fixMe");
		return "Goods";
	}
	@RequestMapping("/create")
	public String addGoods(Model model)
	{
		Good newGoods = new Good();
		model.addAttribute("Goods",newGoods);
		return "AddGoods";
	}
	@RequestMapping("/edit")
	public String editGoods(Model model)
	{
		int GoodsId = 1;
		model.addAttribute("GoodsEdit", new Good());
		return "EditGoods";
	}
	@RequestMapping("/all")
	public String listGoods(Model model)
	{
		model.addAttribute("lstGoods","fixMe");
		return "ListGoods";
	}
	
	@RequestMapping("/delete")
	public String deleteGoods(Model model)
	{
		int GoodsId = 1;
		return " fixMe";
	}


}
