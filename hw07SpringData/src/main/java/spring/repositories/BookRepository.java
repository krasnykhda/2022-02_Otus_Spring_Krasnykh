package spring.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import spring.domain.Author;
import spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    @EntityGraph(attributePaths = "genre")
    List<Book> findAll();

    Book findById(long id);
}
