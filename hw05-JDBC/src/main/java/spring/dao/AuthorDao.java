package spring.dao;

import spring.domain.Author;


import java.util.List;

public interface AuthorDao {

    int count();

    void insert(Author autor);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
