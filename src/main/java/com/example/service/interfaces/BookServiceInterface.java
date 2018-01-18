package com.example.service.interfaces;

import com.example.entity.Book;
import com.example.entity.Publisher;

import java.util.List;

public interface BookServiceInterface {
    Book addBook(Book book);
    Book editBook(Book book);
    void deleteBook(Book book);
    List<Book> getAll();
    List<Book> getByYear(int year);
    List<Book> getByTitle(String title);
    List<Book> getByPublisher(Publisher publisher);
    Book getById(int id);
    List<Book> getMostPopular(int count);
}
