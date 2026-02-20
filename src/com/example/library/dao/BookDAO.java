package com.example.library.dao;

import com.example.library.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    //insert
    int saveBook(Book book) throws SQLException;
    //update
    int updateBookPrice(int bookId, double price) throws SQLException;
    //delete


    //selectALL
    List<Book> getAllBooks() throws SQLException;
    //selectById
    Book getById (int book_id) throws SQLException;
    //selectByTitle
    //SelectBYAuthor

}
