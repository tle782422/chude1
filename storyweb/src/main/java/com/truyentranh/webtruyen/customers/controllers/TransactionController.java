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

import com.truyentranh.webtruyen.customers.models.Transaction;
import com.truyentranh.webtruyen.customers.services.TransactionService;
import com.truyentranh.webtruyen.customers.services.TransactionStatusService;
import com.truyentranh.webtruyen.customers.services.TransactionTypeService;
import com.truyentranh.webtruyen.hr.services.EmployeeService;
import com.truyentranh.webtruyen.parameters.services.ClientService;
import com.truyentranh.webtruyen.parameters.services.ContactService;
import com.truyentranh.webtruyen.parameters.services.SupplierService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionStatusService transactionStatusService;
    @Autowired
    private TransactionTypeService transactionTypeService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;

    public Model addModelAttributes(Model model){
        model.addAttribute("transactionStatuses", transactionStatusService.findAll());
        model.addAttribute("transactionTypes", transactionTypeService.findAll());
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return model;
    }

    @GetMapping("/customers/transactions")
    public String  getAll(Model model){
        List<Transaction> transactions =   transactionService.findAll();
        model.addAttribute("transactions", transactions);
        addModelAttributes(model);
        return "/customers/transactions";
    }

    @GetMapping("/customers/transactionAdd")
    public String addTransaction(Model model){
        addModelAttributes(model);
        return "customers/transactionAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/customers/transaction/{op}/{id}")
    public String editTransaction(@PathVariable Integer id, @PathVariable String op, Model model){
        Transaction transaction = transactionService.findById(id);
        model.addAttribute("transaction", transaction);
        addModelAttributes(model);
        return "/customers/transaction"+ op;
    }

    @PostMapping("/customers/transactions")
    public String save(Transaction transaction){
        transactionService.save(transaction);
        return "redirect:/customers/transactions";
    }

    @RequestMapping(value = "/customers/transactions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        transactionService.delete(id);
        return "redirect:/customers/transactions";
    }
}
