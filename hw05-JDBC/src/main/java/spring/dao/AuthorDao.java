package spring.dao;

import spring.domain.Author;


import java.util.List;

public interface AuthorDao {


    Author insert(Author autor);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
