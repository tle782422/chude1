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


import com.truyentranh.webtruyen.products.models.StoryType;
import com.truyentranh.webtruyen.products.services.StoryTypeService;

@Controller
public class StoryTypeController {

	@Autowired private StoryTypeService storyTypeService;

	//Get All storyTypes
	@GetMapping("/products/storyTypes")
	public String findAll(Model model){
		model.addAttribute("storyTypes", storyTypeService.findAll());
		return "/products/storyTypes";
	}

	@RequestMapping("/products/storyType/{id}")
	@ResponseBody
	public Optional<StoryType> findById(@PathVariable Integer id)
	{
		return storyTypeService.findById(id);
	}

	//Add storyType
	@PostMapping(value="/products/storyTypes")
	public String addNew(StoryType storyType) {
		storyTypeService.save(storyType);
		return "redirect:/products/storyTypes";
	}

	@RequestMapping(value="products/storyType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyTypeService.delete(id);
		return "redirect:/products/storyTypes";
	}

}

