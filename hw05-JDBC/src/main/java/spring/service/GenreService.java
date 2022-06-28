package spring.service;

import spring.domain.Book;
import spring.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre insert(Genre genre);

    Genre update(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
