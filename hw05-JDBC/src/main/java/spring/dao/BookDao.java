package spring.dao;

import spring.domain.Author;
import spring.domain.Book;


import java.util.List;

public interface BookDao {


    Book insert(Book book);
    Book update(Book book);
    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
