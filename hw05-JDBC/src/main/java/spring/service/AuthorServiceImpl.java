package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.AuthorDao;
import spring.dao.BookDao;
import spring.domain.Author;
import spring.domain.Book;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author insert(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public Author update(Author author) {
        return authorDao.update(author);
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        authorDao.deleteById(id);

    }
}
