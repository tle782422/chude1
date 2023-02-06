package com.truyentranh.webtruyen.customers.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.truyentranh.webtruyen.customers.models.Invoice;
import com.truyentranh.webtruyen.customers.services.InvoiceService;
import com.truyentranh.webtruyen.customers.services.InvoiceStatusService;
import com.truyentranh.webtruyen.parameters.services.ClientService;


@Controller
public class InvoiceController {
	
	@Autowired private InvoiceService invoiceService;
	@Autowired private InvoiceStatusService invoiceStatusService;
	@Autowired private ClientService clientService;
	

	@GetMapping("/customers/invoices")
	public String  getAll(Model model){
		List<Invoice> invoices =   invoiceService.findAll();
		model.addAttribute("invoices", invoices);
		return "/customers/invoices";
	}

	@GetMapping("/customers/invoiceAdd")
	public String addInvoice(){
		return "customers/invoiceAdd";
	}

	//The op parameter is either Edit or Details
	@GetMapping("/customers/invoice/{op}/{id}")
	public String editInvoice(@PathVariable Integer id, @PathVariable String op, Model model){
		Invoice invoice = invoiceService.findById(id);
		model.addAttribute("invoice", invoice);
		return "/customers/invoice"+ op;
	}

	@PostMapping("/customers/invoices")
	public String save(Invoice invoice){
		invoiceService.save(invoice);
		return "redirect:/customers/invoices";
	}

	@RequestMapping(value = "/customers/invoices/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public  String delete(@PathVariable Integer id){
		invoiceService.delete(id);
		return "redirect:/customers/invoices";
	}
}
