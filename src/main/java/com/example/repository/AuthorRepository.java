package com.example.repository;

import com.example.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

//@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByLastNameAndFirstName(String lastName, String firstName);

    List<Author> findByCountry(String country);
}
