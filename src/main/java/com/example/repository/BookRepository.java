package com.example.repository;

import com.example.entity.Book;
import com.example.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByYearOfPublication(int year);

    List<Book> findByTitle(String title);

    List<Book> findByPublisher(Publisher publisher);

    Book findByBookId(int id);

}
