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
import org.springframework.web.bind.annotation.ResponseBody;

import com.truyentranh.webtruyen.customers.models.TransactionStatus;
import com.truyentranh.webtruyen.customers.services.TransactionStatusService;

@Controller
public class TransactionStatusController {

    @Autowired
    private TransactionStatusService transactionStatusService;

    @GetMapping("/customers/transactionStatuses")
    public String parameters(Model model){
        List<TransactionStatus> transactionStatuses = transactionStatusService.findAll();
        model.addAttribute("transactionStatuses", transactionStatuses);
        return "/customers/transactionStatuses";
    }

    //Get Job Title by id
    @GetMapping("/customers/transactionStatus/{id}")
    @ResponseBody
    public TransactionStatus getById(@PathVariable Integer id){
        return transactionStatusService.findById(id).orElse(null);
    }

    @PostMapping("/customers/transactionStatuses")
    public String save(TransactionStatus transactionStatus){
        transactionStatusService.save(transactionStatus);
        return "redirect:/customers/transactionStatuses";
    }

    @RequestMapping(value="/customers/transactionStatus/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        transactionStatusService.delete(id);
        return "redirect:/customers/transactionStatus";
    }
}
