package com.truyentranh.webtruyen.parameters.controllers;



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

import com.truyentranh.webtruyen.parameters.models.Contact;
import com.truyentranh.webtruyen.parameters.services.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping("/parameters/contacts")
	public String getAll(Model model){
		List<Contact> contacts =   contactService.findAll();
		model.addAttribute("contacts", contacts);
		return "/parameters/contacts";
	}

	//The Get Contact By Id
	@GetMapping("/parameters/contact/{id}")
	@ResponseBody
	public Contact getContact(@PathVariable Integer id){
		return contactService.findById(id);
	}

	@GetMapping("/parameters/contactAdd")
	public String addContact(){
		return "parameters/contactAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/parameters/contact/{op}/{id}")
	public String editContact(@PathVariable Integer id, @PathVariable String op, Model model){
		Contact contact = contactService.findById(id);
		model.addAttribute("contact", contact);
		return "/parameters/contact"+ op;
	}

	@PostMapping("/parameters/contacts")
	public String save(Contact contact){
		contactService.save(contact);
		return "redirect:/parameters/contacts";
	}

	@RequestMapping(value = "/parameters/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public  String delete(@PathVariable Integer id){
		contactService.delete(id);
		return "redirect:/parameters/contacts";
	}
}
