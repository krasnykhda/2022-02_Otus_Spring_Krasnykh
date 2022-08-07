package spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Comment;
import spring.domain.Genre;
import spring.repositories.AuthorRepository;
import spring.repositories.BookRepository;
import spring.repositories.CommentRepository;
import spring.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;


@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private final List<Author> authors = new ArrayList<Author>();
    private final List<Genre> genres = new ArrayList<Genre>();
    private final List<Book> books = new ArrayList<Book>();

    @ChangeSet(order = "000", id = "dropDB", author = "stvort", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "krasnykhD", runAlways = true)
    public void initAuthors(AuthorRepository repository) {
        var author = repository.save(new Author("Чехов"));
        authors.add(author);
        author = repository.save(new Author("Толстой"));
        authors.add(author);
        author = repository.save(new Author("Пушкин"));
        authors.add(author);


    }

    @ChangeSet(order = "002", id = "initGenres", author = "krasnykhD", runAlways = true)
    public void initGenres(GenreRepository repository) {
        var genre = repository.save(new Genre("Роман"));
        genres.add(genre);
        genre = repository.save(new Genre("Комедия"));
        genres.add(genre);
        genre = repository.save(new Genre("Драма"));
        genres.add(genre);

    }

    @ChangeSet(order = "003", id = "books", author = "krasnykhD", runAlways = true)
    public void initTeachers(BookRepository repository) {
        var book = new Book("Война и мир");
        book.setAuthors(authors.subList(1, 2));
        book.setGenre(genres.get(2));
        repository.save(book);
        books.add(book);
        book = new Book("Евгений Онегин");
        book.setAuthors(authors.subList(2, 3));
        book.setGenre(genres.get(0));
        repository.save(book);
        books.add(book);
        book = new Book("Вишневый сад");
        book.setAuthors(authors.subList(0, 1));
        book.setGenre(genres.get(1));
        repository.save(book);
        books.add(book);

    }
    @ChangeSet(order = "004", id = "comments", author = "krasnykhD", runAlways = true)
    public void initComments(CommentRepository repository) {
        var comment=new Comment("Комментарий к войне и мир");
        comment.setBook(books.get(0));
        repository.save(comment);
        comment=new Comment("Комментарий к Евгению Онегину");
        comment.setBook(books.get(1));
        repository.save(comment);
        comment=new Comment("Комментарий к Вишневому саду");
        comment.setBook(books.get(2));
        repository.save(comment);

    }
}
