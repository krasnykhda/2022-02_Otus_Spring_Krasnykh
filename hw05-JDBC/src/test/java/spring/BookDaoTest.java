package spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.*;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookDaoTest {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @BeforeEach
    void setUp(){
        authorDao = Mockito.mock(AuthorDao.class);
        Mockito.when(authorDao.insert(Mockito.any())).thenReturn(new Author("Лев Толстой"));
    }
    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        bookDao.insert(new Book("Книга 1", new Author("Автор 13"), new Genre("Роман")));
        bookDao.insert(new Book("Книга 2", new Author("Автор 14"), new Genre("Роман")));
        bookDao.insert(new Book("Книга 3", new Author("Автор 15"), new Genre("Роман")));
        bookDao.insert(new Book("Книга 4", new Author("Автор 16"), new Genre("Роман")));
        bookDao.insert(new Book("Книга 5", new Author("Автор 17"), new Genre("Роман")));
        bookDao.insert(new Book("Книга 6", new Author("Автор 18"), new Genre("Роман")));
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
        var book1 = bookDao.insert(new Book("Книга 7", new Author("Автор 1"), new Genre("Роман")));

        var book2 = bookDao.insert(new Book("Книга 8", new Author("Автор 2"), new Genre("Роман")));
        var book3 = bookDao.insert(new Book("Книга 9", new Author("Автор 3"), new Genre("Роман")));
        var book4 = bookDao.insert(new Book("Книга 10", new Author("Автор 4"), new Genre("Роман")));
        var book5 = bookDao.insert(new Book("Книга 11", new Author("Автор 5"), new Genre("Роман")));
        var book6 = bookDao.insert(new Book("Книга 12", new Author("Автор 6"), new Genre("Роман")));
        var bookFromDB = bookDao.getById(book4.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var book = bookDao.insert(new Book("Книга 13", new Author("Автор 1"), new Genre("Роман")));
        var bookFromDB = bookDao.getById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.getId()).isEqualTo(book.getId());
    }
    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author=new Author("Автор 13");
        var book = bookDao.insert(new Book("Книга 13", author, new Genre("Роман")));
        var bookFromDB = bookDao.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Автор 13");
        var author2=authorDao.insert(new Author("Автор 14"));
        book.setAuthor(author2);
        bookDao.update(book);
        bookFromDB = bookDao.getById(book.getId());
        Assertions.assertThat(bookFromDB.getAuthor().getName()).isEqualTo("Автор 14");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var book1 = bookDao.insert(new Book("Книга 13", new Author("Автор 7"), new Genre("Роман")));
        var book2 = bookDao.insert(new Book("Книга 14", new Author("Автор 8"), new Genre("Роман")));
        var book3 = bookDao.insert(new Book("Книга 15", new Author("Автор 9"), new Genre("Роман")));
        var book4 = bookDao.insert(new Book("Книга 16", new Author("Автор 10"), new Genre("Роман")));
        var book5 = bookDao.insert(new Book("Книга 17", new Author("Автор 11"), new Genre("Роман")));
        var book6 = bookDao.insert(new Book("Книга 18", new Author("Автор 12"), new Genre("Роман")));
        bookDao.deleteById(book5.getId());
        var bookList = bookDao.getAll();
        Assertions.assertThat(bookList.size()).isEqualTo(5);
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 18");

    }

}
