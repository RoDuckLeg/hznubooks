package com.example.hznubooks.mapper;

import com.example.hznubooks.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BookMapper {
    List<Book> getBooks();
    Integer addBook(String bookname, String author, String publisher, String category, int status);

    Integer delBook(int bookid);
    Integer updateBook(int bookid);

    Integer borrowBook(int bookid);
}