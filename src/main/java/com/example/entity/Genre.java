package com.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Genre {
    private int genreId;
    private String title;
    private String description;
    private Set<Book> books;

    @Id
    @GeneratedValue
    @Column(name = "Genre_ID", nullable = false)
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Basic
    @Column(name = "Title", nullable = false, length = 30, unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        if (title != null ? !title.equals(genre.title) : genre.title != null) return false;
        if (description != null ? !description.equals(genre.description) : genre.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "genres")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
