package com.example.service;

import com.example.entity.Author;
import com.example.repository.AuthorRepository;
import com.example.service.interfaces.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements AuthorServiceInterface {
    @Autowired
    private AuthorRepository repository;

    public Author addAuthor(Author author) {
        return repository.saveAndFlush(author);
    }

    public Author editAuthor(Author author) {
        return repository.saveAndFlush(author);
    }

    public void deleteAuthor(Author author) {
        repository.delete(author);
    }

    public Author getById(int id) {
        return repository.findOne(id);
    }

    public List<Author> getAll() {
        return repository.findAll();
    }

    public List<Author> getByName(String firstName, String secondName) {
        return repository.findByLastNameAndFirstName(secondName, firstName);
    }

    public List<Author> getByCountry(String country) {
        return repository.findByCountry(country);
    }

    @Override
    public List<Author> getMostPopular(int count) {
        return repository.findAll().stream().sorted(Comparator.comparingInt(a -> a.getBooks().size())).limit(count).collect(Collectors.toList());
    }
}
