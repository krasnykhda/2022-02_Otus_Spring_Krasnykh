package spring.dao;

import spring.domain.Author;
import spring.domain.Genre;


import java.util.List;

public interface GenreDao {


    Genre insert(Genre genre);
    Genre update(Genre genre);
    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
