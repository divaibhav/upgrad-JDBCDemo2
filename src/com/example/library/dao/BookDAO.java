package com.example.library.dao;

import com.example.library.model.Book;

public interface BookDAO {
    //insert
    int saveBook(Book book);
    //update
    int updateBook(int bookId, double price);
    //delete


    //selectALL
    //selectById
    //selectByTitle
    //SelectBYAuthor

}
