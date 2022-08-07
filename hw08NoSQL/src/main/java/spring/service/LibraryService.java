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
        var book = new Book(bookName);
        var authors = new ArrayList<Author>();
        var author = authorService.getById(authorId);
        authors.add(author);
        book.setAuthors(authors);
        book.setGenre(genreService.getById(genreID));
        bookService.save(book);

    }
    @Transactional(readOnly = true)
    public void getById(String id) {
        ioService.out(bookToStringConverter.getBookAsString(bookService.getById(id)));

    }
    @Transactional(readOnly = true)
    public void getAll() {
        ioService.out(bookToStringConverter.getBookAsString(bookService.getAll()));
    }
    @Transactional(readOnly = true)
    public void getCommentsByBookId(String id) {
        ioService.out(commentToStringConverter.getCommentAsString(commentService.getByBookId(id)));
    }

    public void deleteById(String id) {
        bookService.deleteById(id);
        commentService.deleteByBookId(id);

    }


    public void addComment(String bookId, String commentName) {
        var book = bookService.getById((bookId));
        var comment = new Comment(commentName);
        commentService.save(comment);
        comment.setBook(book);
        commentService.save(comment);
    }
    public void addAuthor(String authorName) {
        var author = new Author(authorName);
        authorService.save(author);
    }
    public void  delAuthor(){
        var authorId = ioService.readLn("Введите id удаляемого автора");
        authorService.deleteById(authorId);
        bookService.removeAuthorsArrayElementsById(authorId);
    }
    public void  addGenre(){
        var genreName = ioService.readLn("Введите имя жанра");
        var genre=new Genre(genreName);
        genreService.save(genre);
    }
    public void delComment(String id) {
        commentService.deleteById(id);
    }
}
