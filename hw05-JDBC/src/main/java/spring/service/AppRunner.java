package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

import java.util.List;

@Service
public class AppRunner {
    private final BookService bookService;

    private final IOService ioService;

    public AppRunner(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    public void insertBook() {
        var bookName = ioService.readLn("Введите название книги");
        var authorName = ioService.readLn("Введите имя автора");
        var genreName = ioService.readLn("Введите имя жанра");
        bookService.insert(new Book(bookName, new Author(authorName), new Genre(genreName)));

    }

    public void getById() {
        var id = ioService.readLn("Введите идентификатор книги");
        ioService.out(bookService.getById(Long.parseLong(id)).toString());
    }

    public void getAll() {
        ioService.out(bookService.getAll().toString());
    }

    public void deleteById() {
        var id = ioService.readLn("Введите идентификатор удалямой книги");
        bookService.deleteById(Long.parseLong(id));

    }
}
