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


import com.truyentranh.webtruyen.products.models.StoryStatus;
import com.truyentranh.webtruyen.products.services.StoryStatusService;

@Controller
public class StoryStatusController {
	
	@Autowired private StoryStatusService storyStatusService;
	
	//Get All storyStatuss
	@GetMapping("/products/storyStatuses")
	public String findAll(Model model){		
		model.addAttribute("storyStatuses", storyStatusService.findAll());
		return "/products/storyStatuses";
	}	
	
	@RequestMapping("/products/storyStatus/{id}")
	@ResponseBody
	public Optional<StoryStatus> findById(@PathVariable Integer id)
	{
		return storyStatusService.findById(id);
	}
	
	//Add storyStatus
	@PostMapping(value="/products/storyStatuses")
	public String addNew(StoryStatus storyStatus) {
		storyStatusService.save(storyStatus);
		return "redirect:/products/storyStatuses";
	}	

	@RequestMapping(value="products/storyStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyStatusService.delete(id);
		return "redirect:/products/storyStatuses";
	}
}

