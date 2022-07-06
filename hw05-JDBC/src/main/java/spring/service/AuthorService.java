package spring.service;

import spring.domain.Author;
import spring.domain.Book;

import java.util.List;

public interface AuthorService {
    Author insert(Author author);

    Author update(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
