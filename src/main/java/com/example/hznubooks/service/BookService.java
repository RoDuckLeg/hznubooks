package com.example.hznubooks.service;

import com.example.hznubooks.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    Integer addBook(String bookname, String author, String publisher, String category, int status);

    Integer delBook(int bookid);

    Integer returnBook(int bookid);

    Integer borrowBook(int bookid);
}
