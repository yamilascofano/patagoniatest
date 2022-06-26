package com.example.controller;

import com.example.entity.Loan;
import com.example.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/list")
    public List<Loan> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Loan findById(@PathVariable Long id) throws Exception {
        return loanService.findById(id);
    }

    @PostMapping()
    public Loan saveLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @DeleteMapping("/deleteLoan/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
    }

    @PutMapping("/putLoan/{id}")
    public void putLoan(@PathVariable Long id, @RequestBody Loan loan) throws IllegalStateException {
        loanService.update(id, loan);
    }
}
