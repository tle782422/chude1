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


import com.truyentranh.webtruyen.products.models.StoryModel;
import com.truyentranh.webtruyen.products.services.StoryModelService;

@Controller
public class StoryModelController {

	@Autowired private StoryModelService storyModelService;
	
	//Get All storyModels
	@GetMapping("products/storyModels")
	public String findAll(Model model){		
		model.addAttribute("storyModels", storyModelService.findAll());
		return "/products/storyModels";
	}	
	
	@RequestMapping("/products/storyModel/{id}")
	@ResponseBody
	public Optional<StoryModel> findById(@PathVariable Integer id)
	{
		return storyModelService.findById(id);
	}
	
	//Add storyModel
	@PostMapping(value="/products/storyModels")
	public String addNew(StoryModel storyModel) {
		storyModelService.save(storyModel);
		return "redirect:/products/storyModels";
	}
	
	@RequestMapping(value="storyModel/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyModelService.delete(id);
		return "redirect:/products/storyModels";
	}
}

