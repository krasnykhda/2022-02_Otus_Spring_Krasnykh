package spring.service;

import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);


    Comment getById(long id);
    List<Comment> getByBookID(long id);


    void deleteById(long id);
}
