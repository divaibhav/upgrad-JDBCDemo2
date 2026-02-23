package com.example.library.dao;

import com.example.library.model.Loan;

import java.sql.SQLException;
import java.util.List;

public interface LoanDAO {
    int issueBook(Loan loan);

    int returnBook(int loanId);
    List<Loan> findAll();

    List<Loan> findLoanByMemberId(int memberId) throws SQLException;

    int deleteById(int loanId) throws SQLException;
}
