package com.example.service;

import com.example.entity.Book;
import com.example.entity.Publisher;
import com.example.repository.BookRepository;
import com.example.service.interfaces.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInterface {
    @Autowired
    private BookRepository repository;

    public Book addBook(Book book) {
        return repository.saveAndFlush(book);
    }

    public Book editBook(Book book) {
        return repository.saveAndFlush(book);
    }

    public void deleteBook(Book book) {
        repository.delete(book);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public List<Book> getByYear(int year) {
        return repository.findByYearOfPublication(year);
    }

    public List<Book> getByTitle(String title) {
        List<Book> res = new ArrayList<>(repository.findAll());
        res.removeIf(book -> !book.getTitle().contains(title));
        return res;
        //return repository.findByTitle(title);
    }

    public List<Book> getByPublisher(Publisher publisher) {
        return repository.findByPublisher(publisher);
    }

    public Book getById(int id) {
        return repository.findByBookId(id);
    }

    public List<Book> getMostPopular(int count) {
        return repository.findAll().stream().sorted(Comparator.comparingInt(b -> -b.getOrders().size())).limit(count).collect(Collectors.toList());
    }
}
