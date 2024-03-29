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
    public Comment getById(long id) {
        return commentRepository.findById(id);
    }


    @Override
    public List<Comment> getByBook(Book book) {
        return commentRepository.findByBook(book);
    }
    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);

    }
}
