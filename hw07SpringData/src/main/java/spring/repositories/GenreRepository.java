package spring.repositories;


import org.springframework.data.repository.CrudRepository;
import spring.domain.Author;
import spring.domain.Comment;
import spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> findByName(String s);
}