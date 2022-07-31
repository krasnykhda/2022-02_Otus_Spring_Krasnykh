package spring.service;

import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

   

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
