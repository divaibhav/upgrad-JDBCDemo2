package com.example.library.dao;

import com.example.library.model.Loan;

import java.util.List;

public interface LoanDAO {
    void issueBook(Loan loan);
    void returnBook(int loanId);
    List<Loan> findAll();
}
