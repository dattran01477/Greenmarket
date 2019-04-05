package com.greenmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenmarket.entity.Post;
import com.greenmarket.service.IPostService;


@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	IPostService _postService;
	
	public PostController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/get/{id}")
	private String getById(Model model,@PathVariable(value="id") int id)
	{
		Post post = _postService.getByid(id);
		model.addAttribute("post",post);
		return "ViewPost";
	}

}
