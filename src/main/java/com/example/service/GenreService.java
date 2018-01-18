package com.example.service;

import com.example.entity.Genre;
import com.example.repository.GenreRepository;
import com.example.service.interfaces.GenreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService  implements GenreServiceInterface {
    @Autowired
    private GenreRepository repository;

    public Genre addGenre(Genre genre) {
        return repository.saveAndFlush(genre);
    }

    public Genre editGenre(Genre genre) {
        return repository.saveAndFlush(genre);
    }

    public void deleteGenre(Genre genre) {
        repository.delete(genre);
    }

    public Genre getById(int id) {
        return repository.findOne(id);
    }

    public List<Genre> getAll() {
        return repository.findAll();
    }

    public Genre getByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Genre> getMostPopular(int count) {
        return repository.findAll().stream().sorted(Comparator.comparingInt(g -> g.getBooks().size())).limit(count).collect(Collectors.toList());

    }
}
