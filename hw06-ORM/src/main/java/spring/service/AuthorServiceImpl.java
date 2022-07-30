package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Author;
import spring.domain.Book;
import spring.repositories.AuthorRepository;
import spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }


    @Override
    public Author getById(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        authorRepository.deleteById(id);

    }
}
