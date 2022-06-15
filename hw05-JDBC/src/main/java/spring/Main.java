package spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.dao.AuthorDao;
import spring.dao.BookDao;
import spring.dao.GenreDao;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;
import spring.service.BookService;


import java.sql.SQLException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Main.class);

        AuthorDao authorDao = context.getBean(AuthorDao.class);
        BookDao bookDao=context.getBean(BookDao.class);
        BookService bookService=context.getBean(BookService.class);
        GenreDao genreDao=context.getBean(GenreDao.class);
        Author author=new Author(2, "ivan");
        Genre genre=new Genre(1,"Комедия");
        Book book=new Book(1,"Горе от ума",author,genre);
        System.out.println("All count " + authorDao.count());

        bookService.insert(book);

        System.out.println("All count " + authorDao.count());

        Author ivan = authorDao.getById(2);

        System.out.println("Ivan id: " + ivan.getId() + " name: " + ivan.getName());

        System.out.println(authorDao.getAll());
        System.out.println(bookDao.getAll());
        Console.main(args);
    }
}
