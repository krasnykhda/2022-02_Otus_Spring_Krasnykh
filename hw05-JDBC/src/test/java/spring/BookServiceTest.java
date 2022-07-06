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
@Import({BookServiceImpl.class, BookDaoJdbc.class})
public class BookServiceTest {
    @Autowired
    private BookService bookService;


    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        bookService.insert(new Book("Книга 1", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.insert(new Book("Книга 2", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.insert(new Book("Книга 3", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.insert(new Book("Книга 4", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.insert(new Book("Книга 5", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.insert(new Book("Книга 6", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
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
        var book1 = bookService.insert(new Book("Книга 7", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var book2 = bookService.insert(new Book("Книга 8", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var book3 = bookService.insert(new Book("Книга 9", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var book4 = bookService.insert(new Book("Книга 10", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var book5 = bookService.insert(new Book("Книга 11", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var book6 = bookService.insert(new Book("Книга 12", new Author(1l, "Толстой"), new Genre(1L, "Роман")));
        var bookFromDB = bookService.getById(book4.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var book = bookService.insert(new Book("Книга 13", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.getId()).isEqualTo(book.getId());
    }

    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author = new Author(1L, "Толстой");
        var book = bookService.insert(new Book("Книга 13", author, new Genre(1L, "Роман")));
        var bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Толстой");

        book.setAuthor(new Author(2L, "Чехов"));
        bookService.update(book);
        bookFromDB = bookService.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Чехов");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var book1 = bookService.insert(new Book("Книга 13", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book2 = bookService.insert(new Book("Книга 14", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book3 = bookService.insert(new Book("Книга 15", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book4 = bookService.insert(new Book("Книга 16", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book5 = bookService.insert(new Book("Книга 17", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book6 = bookService.insert(new Book("Книга 18", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookService.deleteById(book5.getId());
        var bookList = bookService.getAll();
        Assertions.assertThat(bookList.size()).isEqualTo(5);
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 18");

    }

}
