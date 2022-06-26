package com.example.service;

import com.example.entity.Loan;
import com.example.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan saveLoan(Loan client) {
        return loanRepository.save(client);
    }

    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe prestamo "));
        loanRepository.delete(loan);
    }

    @Transactional
    public void update(Long id, Loan loan) throws IllegalStateException {
        Loan loan1 = loanRepository.findById(id).orElseThrow(() -> new IllegalStateException("No existe prestamo "));
        loan1.setId(id);
        loan1.setAmount(loan.getAmount());
        loan1.setType(loan.getType());
        loan1.setClientId(loan.getClientId());
        loanRepository.save(loan1);
    }

    public Loan findById(Long id) throws Exception {
        return loanRepository.findById(id).orElseThrow(() -> new Exception("No existe " + id));
    }
}
