package com.truyentranh.webtruyen.hr.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.truyentranh.webtruyen.hr.models.EmployeeType;
import com.truyentranh.webtruyen.hr.repositories.EmployeeTypeRepository;
import com.truyentranh.webtruyen.hr.services.EmployeeTypeService;

@Controller
public class EmployeeTypeController {

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;

	@Autowired
	private EmployeeTypeService employeeTypeService;

	@GetMapping("/hr/employeeTypes")
	public String parameters(Model model){
		List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
		model.addAttribute("employeeTypes", employeeTypes);
		return "hr/employeeTypes";
	}

	//Get Job Title by id
	@GetMapping("/hr/employeeType/{id}")
	@ResponseBody
	public EmployeeType getById(@PathVariable Integer id){
		return employeeTypeService.findById(id).orElse(null);
	}

	@PostMapping("/hr/employeeTypes")
	public String save(EmployeeType employeeType){
		employeeTypeService.save(employeeType);
		return "redirect:/hr/employeeTypes";
	}

	@RequestMapping(value="/hr/employeeType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) {
		employeeTypeService.delete(id);
		return "redirect:/hr/employeeTypes";
	}

}
