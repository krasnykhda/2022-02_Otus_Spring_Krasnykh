package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.domain.*;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {
    private final BookService bookService;
    private final IOService ioService;
    @Autowired
    private EntityManager em;

    public LibraryService(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }


    public void insertBook() {
        var bookName = ioService.readLn("Введите название книги");
        var book = bookService.save(new Book(bookName));
        var authorId = ioService.readLn("Введите ID автора");
        var genreID = ioService.readLn("Введите ID жанра");
        var authors = new ArrayList<Author>();
        var author = em.find(Author.class, Long.parseLong(authorId));
        authors.add(author);
        book.setAuthors(authors);
        book.setGenre(em.find(Genre.class, Long.parseLong(genreID)));
        bookService.save(book);

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

    public void addComment() {
        var bookId = ioService.readLn("Введите ID книги длядобавления комментария");
        var book = bookService.getById(Long.parseLong(bookId)).get();
        var comment = ioService.readLn("Введите комментарий");
        book.addComment(comment);
        bookService.save(book);
    }
}
