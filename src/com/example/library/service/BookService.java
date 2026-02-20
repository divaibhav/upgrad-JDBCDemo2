package com.example.library.service;

import com.example.library.dao.BookDAO;
import com.example.library.dao.BookDAOImpl;
import com.example.library.model.Book;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookService {
    BookDAO bookDAO = new BookDAOImpl();
    Scanner scanner = new Scanner(System.in);
    public void displayAllBooks(){

        try {
            List<Book> allBooks = bookDAO.getAllBooks();
            //allBooks.forEach(System.out::println);
            printBooks(allBooks.toArray(new Book[0]));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void getBookById(){
        System.out.println("enter integer id to find book");
        int bookId = scanner.nextInt();
        try {
            Book bookById = bookDAO.getById(bookId);
            printBooks(bookById);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void printBooks(Book... books){
        System.out.println("Id Title\t\t\t\t\t Author\t\t\t\t\t Year\t Price\t ISBN\t\t Genre\t\t ");
        for (Book book : books) {
            System.out.print(book.getBookId() + "\t");
            System.out.print(book.getTitle() + "\t");
            System.out.print(book.getAuthor() + "\t");
            System.out.print(book.getPublicationYear()+ "\t");
            System.out.print(book.getPrice() + "\t");
            System.out.print(book.getIsbn() + "\t");
            System.out.print(book.getGenre() + "\t");
            System.out.println();
            System.out.println("-".repeat(50));
        }
    }
}
