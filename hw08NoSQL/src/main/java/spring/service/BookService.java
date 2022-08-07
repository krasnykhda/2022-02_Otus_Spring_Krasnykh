package spring.service;

import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

   

    Book getById(String id);

    List<Book> getAll();

    void deleteById(String id);
    void removeAuthorsArrayElementsById(String id);
}
