package spring.repositories;


import spring.domain.Author;
import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Author findById(long id);

    List<Author> findAll();

    List<Author> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
