package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.*;


import javax.persistence.EntityManager;
import java.util.ArrayList;

@Service
public class LibraryService {
    private final BookService bookService;
    private final CommentService commentService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private final IOService ioService;


    public LibraryService(BookService bookService, CommentService commentService, AuthorService authorService, GenreService genreService, IOService ioService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.ioService = ioService;
    }

    public void insertBook() {
        var bookName = ioService.readLn("Введите название книги");
        var book = bookService.save(new Book(bookName));
        var authorId = ioService.readLn("Введите ID автора");
        var genreID = ioService.readLn("Введите ID жанра");
        var authors = new ArrayList<Author>();
        var author = authorService.getById(Long.parseLong(authorId)).get();
        authors.add(author);
        book.setAuthors(authors);
        book.setGenre(genreService.getById(Long.parseLong(genreID)).get());
        bookService.save(book);

    }
    @Transactional
    public void getById(long id) {
       ioService.out(bookService.getById(id).toString());

    }
    @Transactional(readOnly = true)
    public void getAll() {
        ioService.out(bookService.getAll().toString());
    }
    @Transactional(readOnly = true)
    public void getAllComments() {
        ioService.out(commentService.getAll().toString());
    }

    public void deleteById() {
        var id = ioService.readLn("Введите идентификатор удалямой книги");
        bookService.deleteById(Long.parseLong(id));

    }

    public void addComment() {
        var bookId = ioService.readLn("Введите ID книги для добавления комментария");
        var book = bookService.getById(Long.parseLong(bookId)).get();
        var commentName = ioService.readLn("Введите комментарий");
        var comment=new Comment(commentName);
        commentService.save(comment);
        comment.setBook(book);
        commentService.save(comment);
    }
    public void  addAuthor(){
        var authorName = ioService.readLn("Введите имя автора");
        var author=new Author(authorName);
        authorService.save(author);
    }
    public void  addGenre(){
        var genreName = ioService.readLn("Введите имя жанра");
        var genre=new Genre(genreName);
        genreService.save(genre);
    }
}
