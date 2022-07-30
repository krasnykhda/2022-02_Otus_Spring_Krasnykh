package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.*;


import java.util.ArrayList;

@Service
public class LibraryService {
    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final IOService ioService;
    private final BookToStringConverter bookToStringConverter;
    private final CommentToStringConverter commentToStringConverter;

    public LibraryService(BookService bookService, CommentService commentService, AuthorService authorService, GenreService genreService, IOService ioService, BookToStringConverter bookToStringConverter, CommentToStringConverter commentToStringConverter) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
        this.bookToStringConverter = bookToStringConverter;
        this.commentToStringConverter = commentToStringConverter;
    }

    public void insertBook(String bookName, String authorId, String genreID) {
        var book = bookService.save(new Book(bookName));
        var authors = new ArrayList<Author>();
        var author = authorService.getById(Long.parseLong(authorId));
        authors.add(author.get());
        book.setAuthors(authors);
        book.setGenre(genreService.getById(Long.parseLong(genreID)).get());
        bookService.save(book);

    }

    @Transactional(readOnly = true)
    public void getById(long id) {
        ioService.out(bookToStringConverter.getBookAsString(bookService.getById(id).get()));

    }

    @Transactional(readOnly = true)
    public void getAll() {
        ioService.out(bookToStringConverter.getBookAsString(bookService.getAll()));
    }


    public void deleteById(long id) {
        bookService.deleteById(id);

    }

    @Transactional(readOnly = true)
    public void getCommentsByBookId(long id) {
        ioService.out(commentToStringConverter.getCommentAsString(commentService.getByBook(bookService.getById(id).get())));
    }

    public void addComment(String bookId, String commentName) {
        var book = bookService.getById(Long.parseLong(bookId));
        var comment = new Comment(commentName);
        commentService.save(comment);
        comment.setBook(book.get());
        commentService.save(comment);
    }

    public void addAuthor(String authorName) {
        var author = new Author(authorName);
        authorService.save(author);
    }

    public void addGenre(String genreName) {
        var genre = new Genre(genreName);
        genreService.save(genre);
    }

    public void delComment(long id) {
        commentService.deleteById(id);
    }
}
