package com.example.library.dao;

import com.example.library.model.Loan;
import com.example.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOImpl implements LoanDAO {

    @Override
    public int issueBook(Loan loan) {
        return 0;
    }

    @Override
    public int returnBook(int loanId) {
        return 0;
    }

    @Override
    public List<Loan> findAll() {
        return List.of();
    }

    @Override
    public List<Loan> findLoanByMemberId(int memberId) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM loans WHERE member_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, memberId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Loan> loanList = new ArrayList<>();
        while (resultSet.next()) {
            int loanId = resultSet.getInt(1);
            int bookId = resultSet.getInt(2);
            int memberIdFromTable = resultSet.getInt(3);
            Date loanDate = resultSet.getDate(4);
            Date returnDate = resultSet.getDate(5);

            Loan loan = new Loan(loanId, bookId, memberIdFromTable, loanDate, returnDate);
            loanList.add(loan);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return loanList;
    }

    @Override
    public int deleteById(int loanId) throws SQLException {
        String sql = "DELETE FROM loans WHERE loan_id = ?";
        Connection connection = DbConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, loanId);

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        return rows;
    }
}
