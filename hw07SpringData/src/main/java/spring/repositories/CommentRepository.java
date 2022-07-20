package spring.repositories;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @EntityGraph(attributePaths = "book")
    List<Comment> findAll();

    Optional<Comment> findByName(String s);
}
