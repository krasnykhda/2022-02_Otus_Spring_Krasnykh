package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Book;
import spring.domain.Comment;
import spring.repositories.BookRepository;
import spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
       this.commentRepository=commentRepository;
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

   
    @Override
    public Comment getById(String id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public List<Comment> getByBookId(String id) {
        return commentRepository.findCommentByBookId(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteById(id);

    }
    @Override
    @Transactional
    public void deleteByBookId(String id) {
        commentRepository.deleteByBookId(id);

    }
}
