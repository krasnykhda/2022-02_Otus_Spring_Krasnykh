package spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.dao.*;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

@JdbcTest
@Import({BookDaoJdbc.class})
public class BookDaoImplTest {
    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        bookDao.insert(new Book("Книга 1", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.insert(new Book("Книга 2", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.insert(new Book("Книга 3", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.insert(new Book("Книга 4", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.insert(new Book("Книга 5", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.insert(new Book("Книга 6", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var bookList = bookDao.getAll();
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

        var book1 = bookDao.insert(new Book("Книга 7", new Author(2L, "Чехов"), new Genre(2L, "Драма")));
        var book2 = bookDao.insert(new Book("Книга 8", new Author(2L, "Чехов"), new Genre(2L, "Роман")));
        var book3 = bookDao.insert(new Book("Книга 9", new Author(2L, "Чехов"), new Genre(2L, "Роман")));
        var book4 = bookDao.insert(new Book("Книга 10", new Author(2L, "Чехов"), new Genre(2L, "Роман")));
        var book5 = bookDao.insert(new Book("Книга 11", new Author(2L, "Чехов"), new Genre(2L, "Роман")));
        var book6 = bookDao.insert(new Book("Книга 12", new Author(2L, "Чехов"), new Genre(2L, "Роман")));
        var bookFromDB = bookDao.getById(book4.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var book = bookDao.insert(new Book("Книга 13", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var bookFromDB = bookDao.getById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.getId()).isEqualTo(book.getId());
    }

    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author = new Author(1L, "Толстой");
        var book = bookDao.insert(new Book("Книга 13", author, new Genre(1L, "Роман")));
        var bookFromDB = bookDao.getById(book.getId());
        var author2 = new Author(2L, "Чехов");
        book.setAuthor(author2);
        bookDao.update(book);
        bookFromDB = bookDao.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Чехов");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var book1 = bookDao.insert(new Book("Книга 13", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book2 = bookDao.insert(new Book("Книга 14", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book3 = bookDao.insert(new Book("Книга 15", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book4 = bookDao.insert(new Book("Книга 16", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book5 = bookDao.insert(new Book("Книга 17", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        var book6 = bookDao.insert(new Book("Книга 18", new Author(1L, "Толстой"), new Genre(1L, "Роман")));
        bookDao.deleteById(book5.getId());
        var bookList = bookDao.getAll();
        Assertions.assertThat(bookList.size()).isEqualTo(5);
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 18");

    }

}
