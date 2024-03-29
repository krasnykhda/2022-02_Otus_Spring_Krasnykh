package spring.service;

import spring.domain.Author;
import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author save(Author author);


    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
