package com.truyentranh.webtruyen.products.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.truyentranh.webtruyen.parameters.services.SupplierService;
import com.truyentranh.webtruyen.products.models.StoryMaintenance;
import com.truyentranh.webtruyen.products.services.StoryMaintenanceService;
import com.truyentranh.webtruyen.products.services.StoryService;

@Controller
public class StoryMaintenanceController {

	@Autowired private StoryMaintenanceService storyMaintenanceService;
	@Autowired private StoryService storyService;
	@Autowired private SupplierService supplierService;

	public Model addModelAttributes(Model model){
		model.addAttribute("storys", storyService.findAll());
		model.addAttribute("suppliers", supplierService.findAll());
		return model;
	}
	
	//Get All storyMaintenances
	@GetMapping("/products/maintenances")
	public String findAll(Model model){		
		model.addAttribute("maintenances", storyMaintenanceService.findAll());
		return "/products/maintenances";
	}

	@GetMapping("/products/maintenanceAdd")
	public String addMaintenance(Model model){
		addModelAttributes(model);
		return "/products/maintenanceAdd";
	}

	@GetMapping("/products/maintenance/{op}/{id}")
	public String editMaintenance(Model model, @PathVariable Integer id, @PathVariable String op){
		StoryMaintenance maintenance = storyMaintenanceService.findById(id);
		model.addAttribute("maintenance", maintenance);
		addModelAttributes(model);
		return "/products/maintenance"+op;
	}

	//Add storyMaintenance
	@PostMapping("/products/maintenances")
	public String addNew(StoryMaintenance storyMaintenance) {
		storyMaintenanceService.save(storyMaintenance);
		return "redirect:/products/maintenances";
	}
	
	@RequestMapping(value="products/maintenance/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		storyMaintenanceService.delete(id);
		return "redirect:/products/maintenances";
	}

}
