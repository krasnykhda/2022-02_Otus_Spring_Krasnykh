package spring.service;

import spring.domain.Author;
import spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre save(Genre genre);


    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
