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

import com.truyentranh.webtruyen.customers.models.TransactionType;
import com.truyentranh.webtruyen.customers.services.TransactionTypeService;

@Controller
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @GetMapping("/customers/transactionTypes")
    public String parameters(Model model){
        List<TransactionType> transactionTypes = transactionTypeService.findAll();
        model.addAttribute("transactionTypes", transactionTypes);
        return "customers/transactionTypes";
    }

    //Get Job Title by id
    @GetMapping("/customers/transactionType/{id}")
    @ResponseBody
    public TransactionType getById(@PathVariable Integer id){
        return transactionTypeService.findById(id).orElse(null);
    }

    //Add TransactionType
    @PostMapping("/customers/transactionTypes")
    public String addNew(TransactionType transactionType) {
        transactionTypeService.save(transactionType);
        return "redirect:/customers/transactionTypes";
    }

    @RequestMapping(value="/customers/transactionType/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        transactionTypeService.delete(id);
        return "redirect:/customers/transactionTypes";
    }
}
