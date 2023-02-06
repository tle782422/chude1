package com.truyentranh.webtruyen.products.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.truyentranh.webtruyen.parameters.services.ClientService;
import com.truyentranh.webtruyen.parameters.services.LocationService;
import com.truyentranh.webtruyen.products.models.StoryHire;
import com.truyentranh.webtruyen.products.services.StoryHireService;
import com.truyentranh.webtruyen.products.services.StoryService;

@Controller
public class StoryHireController {
	
	@Autowired private StoryHireService storyHireService;
	@Autowired private ClientService clientService;
	@Autowired private LocationService locationService;
	@Autowired private StoryService storyService;

	public Model addModelAttributes(Model model){
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("storys", storyService.findAll());
		return model;
	}

	//Get All storyHires
	@GetMapping("/products/hires")
	public String findAll(Model model){		
		model.addAttribute("hires", storyHireService.findAll());
		return "/products/hires";
	}

	@GetMapping("/products/hireAdd")
	public String addHire(Model model){
		addModelAttributes(model);
		return "/products/hireAdd";
	}

	@GetMapping("/products/hire/{op}/{id}")
	public String editHire(Model model, @PathVariable Integer id, @PathVariable String op){
		StoryHire hire = storyHireService.findById(id);
		model.addAttribute("hire", hire);
		addModelAttributes(model);
		return "/products/hire"+op;
	}

	//Add storyHire
	@PostMapping("/products/hires")
	public String addNew(StoryHire storyHire) {
		storyHireService.save(storyHire);
		return "redirect:/products/hires";
	}
	
	@RequestMapping(value="products/hire/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyHireService.delete(id);
		return "redirect:/products/hires";
	}

}
