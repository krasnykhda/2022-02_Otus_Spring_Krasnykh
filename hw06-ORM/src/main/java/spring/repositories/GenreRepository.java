package spring.repositories;


import spring.domain.Author;
import spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);

    Genre findById(long id);

    List<Genre> findAll();

    List<Genre> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
