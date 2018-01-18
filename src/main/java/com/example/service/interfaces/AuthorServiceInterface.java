package com.example.service.interfaces;



import com.example.entity.Author;

import java.util.List;

public interface AuthorServiceInterface {
    Author addAuthor(Author author);
    Author editAuthor(Author author);
    void deleteAuthor(Author author);
    Author getById(int id);
    List<Author> getAll();
    List<Author> getByName(String firstName, String secondName);
    List<Author> getByCountry(String country);
    List<Author> getMostPopular(int count);
}
