package spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;
import spring.repositories.BookRepository;


import javax.persistence.EntityManager;
import java.util.ArrayList;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
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
            bookRepository.save(book);
        }

        em.clear();

        var bookList = bookRepository.findAll();
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
            bookRepository.save(book);
        }
        var bookFromDB = bookRepository.findById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var author = new Author("Толстой");
        var authors = new ArrayList<Author>();
        authors.add(author);
        var book = bookRepository.save(new Book("Книга 13", authors, new Genre("Роман")));
        var bookFromDB = bookRepository.findById(book.getId());
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.getId()).isEqualTo(book.getId());
    }
    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author = em.find(Author.class, 4L);
        var genre = em.find(Genre.class, 5L);
        var authors = new ArrayList<Author>();
        authors.add(author);
        var book = bookRepository.save(new Book("Книга 13", authors, genre));
        var bookFromDB = bookRepository.findById(book.getId());
        var author2 = em.find(Author.class, 5L);
        var authors2 = new ArrayList<Author>();
        authors2.add(author2);
        book.setAuthors(authors2);
        bookRepository.save(book);
        bookFromDB = bookRepository.findById(book.getId());
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
            bookRepository.save(book);
        }
        bookRepository.deleteById(1L);
        bookRepository.deleteById(2L);
        bookRepository.deleteById(3L);
        var bookList = bookRepository.findAll();
        Assertions.assertThat(bookList).hasSize(10);
        Assertions.assertThat(bookList.get(0).getName()).isEqualTo("Книга 1");

    }

}
