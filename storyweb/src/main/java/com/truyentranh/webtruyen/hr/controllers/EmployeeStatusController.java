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

import com.truyentranh.webtruyen.hr.models.EmployeeStatus;
import com.truyentranh.webtruyen.hr.services.EmployeeStatusService;

@Controller
public class EmployeeStatusController {

    @Autowired
    private EmployeeStatusService employeeStatusService;

    @GetMapping("/hr/employeeStatuses")
    public String parameters(Model model){
        List<EmployeeStatus> employeeStatuses = employeeStatusService.findAll();
        model.addAttribute("employeeStatuses", employeeStatuses);
        return "hr/employeeStatuses";
    }

    //Get Job Title by id
    @GetMapping("/hr/employeeStatus/{id}")
    @ResponseBody
    public EmployeeStatus getById(@PathVariable Integer id){
        return employeeStatusService.findById(id).orElse(null);
    }

    @PostMapping("/hr/employeeStatuses")
    public String save(EmployeeStatus employeeStatus){
        employeeStatusService.save(employeeStatus);
        return "redirect:/hr/employeeStatuses";
    }

    @RequestMapping(value="/hr/employeeStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        employeeStatusService.delete(id);
        return "redirect:/hr/employeeStatuses";
    }
    
}
