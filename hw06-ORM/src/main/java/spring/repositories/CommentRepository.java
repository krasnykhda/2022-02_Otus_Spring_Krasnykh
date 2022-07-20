package spring.repositories;


import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findByName(String name);
    List<Comment> findByBookID(long id);

    void updateNameById(long id, String name);

    void deleteById(long id);
}
