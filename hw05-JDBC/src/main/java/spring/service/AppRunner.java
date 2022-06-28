package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

@Service
public class AppRunner {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final IOService ioService;

    public AppRunner(BookService bookService, AuthorService authorService, GenreService genreService, IOService ioService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
    }

    public void insertBook() {
        var bookName = ioService.readLn("Введите название книги");
        var authorName = ioService.readLn("Введите имя автора");
        var author=authorService.insert(new Author(authorName));
        var genreName = ioService.readLn("Введите имя жанра");
        var genre=genreService.insert(new Genre(genreName));
        bookService.insert(new Book(bookName, author, genre));

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
