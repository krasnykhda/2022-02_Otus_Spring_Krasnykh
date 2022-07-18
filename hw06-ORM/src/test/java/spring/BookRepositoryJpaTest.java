package spring;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;
import spring.repositories.BookRepositoryJpa;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@DataJpaTest
@Import({BookRepositoryJpa.class})
public class BookRepositoryJpaTest {
    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        var genre = em.find(Genre.class, 4L);
        var author = em.find(Author.class, 4L);
        var authors = new ArrayList<Author>();
        authors.add(author);
        for (int i = 1; i < 11; i++) {
            var book = new Book("Книга " + i, authors, genre);
            bookRepositoryJpa.save(book);
        }

        em.clear();

        var bookList = bookRepositoryJpa.findAll();
        Assertions.assertThat(bookList).hasSize(13);
        for (int i = 4; i < 14; i++) {
            Assertions.assertThat(bookList.get(i - 1).getName()).isEqualTo("Книга " + (i - 3));
        }
    }

    @Test
    @DisplayName("Метод получения книги по идентификатору работает корректно")
    void getByIdTest() {
        var author = em.find(Author.class, 4L);
        var authors = new ArrayList<Author>();
        authors.add(author);
        var genre = em.find(Genre.class, 4L);
        Book book = null;
        for (int i = 1; i < 11; i++) {
            book = new Book("Книга " + i, authors, genre);
            bookRepositoryJpa.save(book);
        }
        var bookFromDB = bookRepositoryJpa.findById(book.getId()).get();
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var author = new Author("Толстой");
        var authors = new ArrayList<Author>();
        authors.add(author);
        var book = bookRepositoryJpa.save(new Book("Книга 13", authors, new Genre("Роман")));
        var bookFromDB = bookRepositoryJpa.findById(book.getId());
        Assertions.assertThat(bookFromDB.get().getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.get().getId()).isEqualTo(book.getId());
    }
    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author = em.find(Author.class, 4L);
        var genre = em.find(Genre.class, 5L);
        var authors = new ArrayList<Author>();
        authors.add(author);
        var book = bookRepositoryJpa.save(new Book("Книга 13", authors, genre));
        var bookFromDB = bookRepositoryJpa.findById(book.getId()).get();
        var author2 = em.find(Author.class, 5L);
        var authors2 = new ArrayList<Author>();
        authors2.add(author2);
        book.setAuthors(authors2);
        bookRepositoryJpa.save(book);
        bookFromDB = bookRepositoryJpa.findById(book.getId()).get();
        Assertions.assertThat(bookFromDB.getAuthors().get(0).getName()).isEqualTo("Чехов");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var author = em.find(Author.class, 4L);
        var authors = new ArrayList<Author>();
        authors.add(author);
        var genre = em.find(Genre.class, 5L);
        Book book = null;
        for (int i = 1; i < 11; i++) {
            book = new Book("Книга " + i, authors, genre);
            bookRepositoryJpa.save(book);
        }
        bookRepositoryJpa.deleteById(1);
        bookRepositoryJpa.deleteById(2);
        bookRepositoryJpa.deleteById(3);
        var bookList = bookRepositoryJpa.findAll();
        Assertions.assertThat(bookList).hasSize(10);
        Assertions.assertThat(bookList.get(0).getName()).isEqualTo("Книга 1");

    }

}
