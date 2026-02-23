package com.example.library.main;

import com.example.library.service.BookService;
import com.example.library.service.MemberService;

public class LibraryMain {
    public static void main(String[] args) {
  /*
   //testing connection
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
        }*/
        BookService bookService = new BookService();
        // bookService.displayAllBooks();
        // bookService.getBookById();
        MemberService memberService = new MemberService();
        //memberService.addMember();
        //memberService.getMemberById();
        memberService.deleteById();
    }
}
/*
1 register --adding new member
2. Display all books
3. Add book
4.
 */
