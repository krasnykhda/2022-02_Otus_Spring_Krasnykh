package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.BookDao;
import spring.domain.Book;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book insert(Book book) {
        return bookDao.insert(book);
    }

    @Override
    public Book update(Book book) {
        return bookDao.update(book);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        bookDao.deleteById(id);

    }
}
