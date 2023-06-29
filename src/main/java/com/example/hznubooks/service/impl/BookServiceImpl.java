package com.example.hznubooks.service.impl;

import com.example.hznubooks.entity.Book;
import com.example.hznubooks.mapper.BookMapper;
import com.example.hznubooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<Book> getBooks() {
        return bookMapper.getBooks();
    }

    @Override
    public Integer addBook(String bookname, String author, String publisher, String category, int status) {
        return bookMapper.addBook(bookname, author, publisher, category, status);
    }

    @Override
    public Integer delBook(int bookid) {
        bookMapper.delBook(bookid);
        return 1;
    }

    @Override
    public Integer returnBook(int bookid) {
        bookMapper.updateBook(bookid);
        return 1;
    }

    @Override
    public Integer borrowBook(int bookid) {
        bookMapper.borrowBook(bookid);
        return 1;
    }
}
