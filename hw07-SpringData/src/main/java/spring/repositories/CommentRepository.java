package spring.repositories;


import org.springframework.data.repository.CrudRepository;
import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAll();

    Optional<Comment> findByName(String s);
}
