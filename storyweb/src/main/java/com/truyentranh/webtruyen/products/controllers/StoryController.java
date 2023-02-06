package com.truyentranh.webtruyen.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.truyentranh.webtruyen.hr.services.EmployeeService;
import com.truyentranh.webtruyen.parameters.services.LocationService;
import com.truyentranh.webtruyen.products.models.Story;
import com.truyentranh.webtruyen.products.services.StoryMakeService;
import com.truyentranh.webtruyen.products.services.StoryModelService;
import com.truyentranh.webtruyen.products.services.StoryService;
import com.truyentranh.webtruyen.products.services.StoryStatusService;
import com.truyentranh.webtruyen.products.services.StoryTypeService;

@Controller
public class StoryController {
	
	@Autowired private StoryService storyService;
	@Autowired private StoryTypeService storyTypeService;
	@Autowired private StoryMakeService storyMakeService;
	@Autowired private StoryModelService storyModelService;
	@Autowired private LocationService locationService;
	@Autowired private EmployeeService employeeService ;
	@Autowired private StoryStatusService storyStatusService;

	public Model addModelAttributes(Model model){
		model.addAttribute("storys", storyService.findAll());
		model.addAttribute("storyTypes", storyTypeService.findAll());
		model.addAttribute("storyModels", storyModelService.findAll());
		model.addAttribute("storyMakes", storyMakeService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("storyStatuses", storyStatusService.findAll());
		return model;
	}

	//Get All storys
	@GetMapping("/products/storys")
	public String findAll(Model model){		
		addModelAttributes(model);
		return "/products/storys";
	}

	@GetMapping("/products/storyAdd")
	public String addstory(Model model){
		addModelAttributes(model);
		return "products/storyAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/products/story/{op}/{id}")
	public String editstory(@PathVariable Integer id, @PathVariable String op, Model model){
		Story story = storyService.findById(id);
		model.addAttribute("story", story);
		addModelAttributes(model);
		return "/products/story"+ op; //returns storyEdit or storyDetails
	}

	//Add story
	@PostMapping("/products/storys")
	public String addNew(Story story) {
		storyService.save(story);
		return "redirect:/products/storys";
	}	

	@RequestMapping(value="/products/story/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyService.delete(id);
		return "redirect:/products/storys";
	}
}

