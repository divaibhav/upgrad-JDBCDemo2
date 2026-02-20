package com.example.library.dao;

import com.example.library.model.Book;
import com.example.library.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{
    @Override
    public int saveBook(Book book) throws SQLException {
        Connection conn = DbConnection.getConnection();
        String insertSql = "INSERT INTO BOOKS (title, author, publication_year, price, isbn, genre) VALUES(?, ?, ?, ?, ?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        //setting query parameters
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setInt(3, book.getPublicationYear());
        pstmt.setDouble(4, book.getPrice());
        pstmt.setString(4, book.getIsbn());
        pstmt.setString(6, book.getGenre());

        //execute the query
        int rowsAffected = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return rowsAffected;


    }

    @Override
    public int updateBookPrice(int bookId, double price) throws SQLException {
        //do it by your self
        //UPDATE `books` SET `price` = '14.00' WHERE (`book_id` = '11');
        Connection connection = DbConnection.getConnection();
        String updateSQL = "UPDATE books SET price = ? WHERE book_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
        preparedStatement.setDouble(1, price);
        preparedStatement.setInt(2, bookId);

        int rowsAffected = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

        return rowsAffected;
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
       List<Book> allBooks = new ArrayList<>();
       //connection
        //query sql
        //preparedStatement
        //execute query
        // process resultset
        Connection connection = DbConnection.getConnection();
        String selectSql = "SELECT * FROM books";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        ResultSet resultSet = preparedStatement.executeQuery();

        //process resultset
        while(resultSet.next()){
            int bookId = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("publication_year");
            double price = resultSet.getDouble("price");
            String isbn = resultSet.getString("isbn");
            String genre = resultSet.getString("genre");

            Book book = new Book(bookId, title, author, year, price, isbn, genre);
            allBooks.add(book);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return allBooks;
    }

    @Override
    public Book getById(int book_id) throws SQLException {
        Book book = null;
        Connection connection = DbConnection.getConnection();
        String selectSql = "SELECT * FROM books WHERE book_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, book_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        //process resultset
        while(resultSet.next()){
            int bookId = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("publication_year");
            double price = resultSet.getDouble("price");
            String isbn = resultSet.getString("isbn");
            String genre = resultSet.getString("genre");

            book = new Book(bookId, title, author, year, price, isbn, genre);

        }
        resultSet.close();
        preparedStatement.close();
        connection.close();


        return book;
    }
}
