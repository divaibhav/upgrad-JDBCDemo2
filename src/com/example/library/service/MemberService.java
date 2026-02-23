package com.example.library.service;

import com.example.library.dao.LoanDAO;
import com.example.library.dao.LoanDAOImpl;
import com.example.library.dao.MemberDAO;
import com.example.library.dao.MemberDAOImpl;
import com.example.library.model.Loan;
import com.example.library.model.Member;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    MemberDAO memberDAO = new MemberDAOImpl();

    public void addMember() {
        //get data from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter following details to add a member");
        System.out.println("enter full name");
        String fullName = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        System.out.println("enter phone number without 0 or country code");
        String phoneNo = scanner.nextLine();
        scanner.close();
        //create object of member
        Member member = new Member();
        member.setFullName(fullName);
        member.setEmail(email);
        member.setPhone(phoneNo);

        try {
            int response = memberDAO.save(member);
            if (response == 1) {
                System.out.println("Member added successfully");
            }
        } catch (SQLException e) {
            System.out.println("Member Not added");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    public void getMemberById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter member id");


        try {
            int id = scanner.nextInt();
            Member member = memberDAO.findById(id);
            if (member != null) {
                System.out.println(member);
            } else {
                System.out.println("invalid id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("enter id in integer only");
        }
    }

    public void deleteById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter member id to delete member");
        try {
            int memberId = scanner.nextInt();
            // conditional call, if all the issued books are returned
            // then delete all the loan entries for the given member id
            // otherwise cannot delete
            LoanDAO loanDAO = new LoanDAOImpl();
            List<Loan> loanByMemberId = loanDAO.findLoanByMemberId(memberId);
            boolean flag = isBookIssued(loanByMemberId);
            if (!flag) {
                //delete loans id, delete by loan id fro loan table
                deleteLoans(loanByMemberId);
                int rows = memberDAO.delete(memberId);
                if (rows > 0) {
                    System.out.println("Deleted successfully");
                } else {
                    System.out.println("Not deleted, invalid id");
                }
            } else {
                System.out.println("Books are issued against the Member Id, please return books deletion");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("enter id in integer only");
        }

    }

    private void deleteLoans(List<Loan> loanByMemberId) throws SQLException {
        LoanDAO loanDAO = new LoanDAOImpl();
        for (Loan loan : loanByMemberId) {
            loanDAO.deleteById(loan.getLoanId());
        }
    }

    private boolean isBookIssued(List<Loan> loanByMemberId) {
        long count = loanByMemberId
                .stream()
                .filter(loan -> loan.getReturnDate() == null)
                .count();

        return count != 0;
    }

}
