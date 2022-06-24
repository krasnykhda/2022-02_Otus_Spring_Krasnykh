package spring.service;

import spring.domain.Book;

import java.util.List;

public interface BookService {
    Book insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
