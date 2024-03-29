package spring.repositories;


import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Book findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
