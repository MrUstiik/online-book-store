package com.example.service.interfaces;



import com.example.entity.Genre;

import java.util.List;

public interface GenreServiceInterface {
    Genre addGenre(Genre genre);
    Genre editGenre(Genre genre);
    void deleteGenre(Genre genre);
    Genre getById(int id);
    List<Genre> getAll();
    Genre getByTitle(String title);
    List<Genre> getMostPopular(int count);
}
