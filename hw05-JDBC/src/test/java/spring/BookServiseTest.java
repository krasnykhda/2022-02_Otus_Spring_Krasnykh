package spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import spring.dao.*;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;
import spring.service.BookService;
import spring.service.BookServiceImpl;

@JdbcTest
@Import({BookServiceImpl.class,BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookServiseTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        bookService.insert(new Book("Книга 1", new Author("Автор 13"), new Genre("Роман")));
        bookService.insert(new Book("Книга 2", new Author("Автор 14"), new Genre("Роман")));
        bookService.insert(new Book("Книга 3", new Author("Автор 15"), new Genre("Роман")));
        bookService.insert(new Book("Книга 4", new Author("Автор 16"), new Genre("Роман")));
        bookService.insert(new Book("Книга 5", new Author("Автор 17"), new Genre("Роман")));
        bookService.insert(new Book("Книга 6", new Author("Автор 18"), new Genre("Роман")));
        var bookList = bookService.getAll();
        Assertions.assertThat(bookList.size()).isEqualTo(6);
        Assertions.assertThat(bookList.get(0).getName()).isEqualTo("Книга 1");
        Assertions.assertThat(bookList.get(1).getName()).isEqualTo("Книга 2");
        Assertions.assertThat(bookList.get(2).getName()).isEqualTo("Книга 3");
        Assertions.assertThat(bookList.get(3).getName()).isEqualTo("Книга 4");
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 5");
        Assertions.assertThat(bookList.get(5).getName()).isEqualTo("Книга 6");
    }

    @Test
    @DisplayName("Метод получения книги по идентификатору работает корректно")
    void getByIdTest() {
        var book1 = bookService.insert(new Book("Книга 7", new Author("Автор 1"), new Genre("Роман")));

        var book2 = bookService.insert(new Book("Книга 8", new Author("Автор 2"), new Genre("Роман")));
        var book3 = bookService.insert(new Book("Книга 9", new Author("Автор 3"), new Genre("Роман")));
        var book4 = bookService.insert(new Book("Книга 10", new Author("Автор 4"), new Genre("Роман")));
        var book5 = bookService.insert(new Book("Книга 11", new Author("Автор 5"), new Genre("Роман")));
        var book6 = bookService.insert(new Book("Книга 12", new Author("Автор 6"), new Genre("Роман")));
        var bookFromDB = bookService.getById(book4.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var book = bookService.insert(new Book("Книга 13", new Author("Автор 1"), new Genre("Роман")));
        var bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.getId()).isEqualTo(book.getId());
    }
    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author=new Author("Автор 13");
        var book = bookService.insert(new Book("Книга 13", author, new Genre("Роман")));
        var bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Автор 13");
        var author2=authorDao.insert(new Author("Автор 14"));
        book.setAuthor(author2);
        bookService.update(book);
        bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Автор 14");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var book1 = bookService.insert(new Book("Книга 13", new Author("Автор 7"), new Genre("Роман")));
        var book2 = bookService.insert(new Book("Книга 14", new Author("Автор 8"), new Genre("Роман")));
        var book3 = bookService.insert(new Book("Книга 15", new Author("Автор 9"), new Genre("Роман")));
        var book4 = bookService.insert(new Book("Книга 16", new Author("Автор 10"), new Genre("Роман")));
        var book5 = bookService.insert(new Book("Книга 17", new Author("Автор 11"), new Genre("Роман")));
        var book6 = bookService.insert(new Book("Книга 18", new Author("Автор 12"), new Genre("Роман")));
        bookService.deleteById(book5.getId());
        var bookList = bookService.getAll();
        Assertions.assertThat(bookList.size()).isEqualTo(5);
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 18");

    }

}
