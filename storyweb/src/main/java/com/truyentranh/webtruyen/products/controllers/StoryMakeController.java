package com.truyentranh.webtruyen.products.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.truyentranh.webtruyen.products.models.StoryMake;
import com.truyentranh.webtruyen.products.services.StoryMakeService;



@Controller
public class StoryMakeController {
	
	@Autowired private StoryMakeService storyMakeService;
	
	//Get All storyMakes
	@GetMapping("products/storyMakes")
	public String findAll(Model model){		
		model.addAttribute("storyMakes", storyMakeService.findAll());
		return "/products/storyMakes";
	}	
	
	@RequestMapping("/products/storyMakes/{id}")
	@ResponseBody
	public Optional<StoryMake> findById(@PathVariable Integer id)
	{
		return storyMakeService.findById(id);
	}
	
	//Add storyMake
	@PostMapping(value="/products/storyMakes")
	public String addNew(StoryMake storyMake) {
		storyMakeService.save(storyMake);
		return "redirect:/products/storyMakes";
	}
	
	@RequestMapping(value="storyMake/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		storyMakeService.delete(id);
		return "redirect:/products/storyMakes";
	}
}

