package com.truyentranh.webtruyen.hr.controllers;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.truyentranh.webtruyen.constant.Constants;
import com.truyentranh.webtruyen.hr.models.Employee;
import com.truyentranh.webtruyen.hr.services.EmployeeService;
import com.truyentranh.webtruyen.hr.services.EmployeeTypeService;
import com.truyentranh.webtruyen.hr.services.JobTitleService;
import com.truyentranh.webtruyen.parameters.services.CountryService;
import com.truyentranh.webtruyen.parameters.services.StateService;
import com.truyentranh.webtruyen.utils.CommonUtil;
import com.truyentranh.webtruyen.utils.FileHelper;

@Controller
public class EmployeeController {
	
	@Autowired private EmployeeService employeeService;
	@Autowired private StateService stateService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private EmployeeTypeService employeeTypeService;
	@Autowired private CountryService countryService;

	public Model addModelAttributes(Model model){
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("states", stateService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("jobTitles", jobTitleService.findAll());
		model.addAttribute("employeeTypes", employeeTypeService.findAll());
		return model;
	}

	//Get All Employees
	@GetMapping("hr/employees")
	public String findAll(Model model){
		addModelAttributes(model);
		return "/hr/employees";
	}	

	@GetMapping("/hr/employeeAdd")
	public String addEmployee(Model model){
		addModelAttributes(model);
		return "/hr/employeeAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/hr/employee/{op}/{id}")
	public String editEmployee(@PathVariable Integer id, @PathVariable String op, Model model){
		Employee employee = employeeService.findById(id);
		model.addAttribute("employee", employee);
		addModelAttributes(model);
		return "/hr/employee"+ op; //returns employeeEdit or employeeDetails
	}

	//Add Employee
	@PostMapping("/hr/employees")
	public String addNew(Employee employee,@RequestParam("image") MultipartFile file) throws IOException {
		 String randomStr = new BigInteger(130, new SecureRandom()).toString(32).substring(0, 6);
		 String newFileName = CommonUtil.cvDateToString(new Date(), Constants.DATE_FORMAT_FOR_FILE_NAME) 
				 + Constants.COMMON_HYPHEN + randomStr;
		 employee.setPhoto(newFileName);
		 Employee savedUser = employeeService.save(employee);
		 String uploadDir = "C:\\Users\\Public\\Documents"
		 			+ "\\SpringApplications\\storyweb\\src\\"
			 		+ "main\\resources\\static\\img\\employee";
		 FileHelper.saveFile(uploadDir, newFileName, file);
		 
		return "redirect:/hr/employees";
	}	
	
//	@PostMapping(value = {"/api/add"})
//	@ResponseBody
//	public ResponseDataModel add(@ModelAttribute BrandEntity brandEntity) {
//		return brandService.add(brandEntity);
//	}

	@RequestMapping(value="/hr/employee/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable Integer id) throws IOException {
		Employee employee = employeeService.findById(id);
		FileHelper.deleteFile(employee.getPhoto());		
		employeeService.delete(id);
		return "redirect:/hr/employees";
	}	

	@RequestMapping(value="/employees/uploadPhoto", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		File newFile = new File("D:\\SOLUTIONS\\storyweb\\uploads" + file.getOriginalFilename());
		newFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(newFile);
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
	}	

	@PostMapping("/employees/uploadPhoto2")
	public String uploadFile2(@RequestParam("file") MultipartFile file, Principal principal) 
			throws IllegalStateException, IOException {
			String baseDirectory = "C:\\Users\\Public\\Documents\\SpringApplications\\storyweb\\src\\"
					+ "main\\resources\\static\\img\\employee" ;
			file.transferTo(new File(baseDirectory + principal.getName() + ".jpg"));
			return "redirect:/employees";
	}

	@RequestMapping(value="/employee/profile")
	public String profile(Model model, Principal principal) {
		String un = principal.getName();
		addModelAttributes(model);
		model.addAttribute("employee", employeeService.findByUsername(un));
		return "profile";
	}
}
