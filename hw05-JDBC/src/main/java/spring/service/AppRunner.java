package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

import java.util.List;

@Service
public class AppRunner {
    private BookService bookService;

    private IOService ioService;

    public AppRunner(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    public void insertBook() {
        var bookName=ioService.readLn("Введите название книги");
        bookService.insert(new Book(bookName,new Author("Вася"),new Genre("Роман")));
    }

    public Book getById(long id) {
        return bookService.getById(id);
    }

    public List<Book> getAll() {
        return bookService.getAll();
    }

    public void deleteById(long id) {
        bookService.deleteById(id);

    }
}
