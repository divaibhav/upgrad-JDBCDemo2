package com.example.library.main;

import com.example.library.service.BookService;
import com.example.library.util.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class LibraryMain {
    public static void main(String[] args) {
        try {
            Connection conn = DbConnection.getConnection();
            if(conn != null){
                System.out.println("connection established");
            }
            else{
                System.out.println("not connected");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting " + e.getMessage());
        }
        BookService bookService = new BookService();
        bookService.displayAllBooks();
        bookService.getBookById();
    }
}
