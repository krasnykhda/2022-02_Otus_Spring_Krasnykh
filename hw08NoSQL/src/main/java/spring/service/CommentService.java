package spring.service;

import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);


    Comment getById(String id);

    List<Comment> getByBookId(String id);

    void deleteById(String id);
    void deleteByBookId(String id);
}
