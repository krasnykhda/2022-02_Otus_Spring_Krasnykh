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
//@SpringBootTest
@Import({BookRepositoryJpa.class})
public class BookRepositoryJpaTest {
    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Метод получения списка книг работает корректно")
    void getAllTest() {
        var genre=em.find(Genre.class, 1L);
        var author=em.find(Author.class, 1L);
        var authors=new ArrayList<Author>();
        authors.add(author);
        bookRepositoryJpa.save(new Book("Книга 1", authors, genre));
        bookRepositoryJpa.save(new Book("Книга 2", authors, genre));
        bookRepositoryJpa.save(new Book("Книга 3", authors, genre));
        bookRepositoryJpa.save(new Book("Книга 4", authors, genre));
        bookRepositoryJpa.save(new Book("Книга 5", authors, genre));
        bookRepositoryJpa.save(new Book("Книга 6", authors, genre));
        var bookList = bookRepositoryJpa.findAll();
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
        var author=em.find(Author.class, 2L);
        var authors=new ArrayList<Author>();
        authors.add(author);
        var genre=em.find(Genre.class, 2L);
        var book1 = bookRepositoryJpa.save(new Book("Книга 7", authors, genre));
        var book2 = bookRepositoryJpa.save(new Book("Книга 8",authors, genre));
        var book3 = bookRepositoryJpa.save(new Book("Книга 9", authors, genre));
        var book4 = bookRepositoryJpa.save(new Book("Книга 10", authors, genre));
        var book5 = bookRepositoryJpa.save(new Book("Книга 11", authors, genre));
        var book6 = bookRepositoryJpa.save(new Book("Книга 12", authors, genre));
        var bookFromDB = bookRepositoryJpa.findById(book4.getId()).get();
        Assertions.assertThat(bookFromDB.getName()).isEqualTo("Книга 10");
    }

    @Test
    @DisplayName("Метод вставки книги работает корректно")
    void insertTest() {
        var author=new Author( "Толстой");
        var authors=new ArrayList<Author>();
        authors.add(author);
        var book = bookRepositoryJpa.save(new Book("Книга 13",authors, new Genre( "Роман")));
        var bookFromDB = bookRepositoryJpa.findById(book.getId());
        Assertions.assertThat(bookFromDB.get().getName()).isEqualTo("Книга 13");
        Assertions.assertThat(bookFromDB.get().getId()).isEqualTo(book.getId());
    }

    @Test
    @DisplayName("Метод обновления книги работает корректно")
    void updatetTest() {
        var author=em.find(Author.class, 1L);
        var genre=em.find(Genre.class, 2L);
        var authors=new ArrayList<Author>();
        authors.add(author);
        var book = bookRepositoryJpa.save(new Book("Книга 13", authors, genre));
        var bookFromDB = bookRepositoryJpa.findById(book.getId()).get();
        var author2 = em.find(Author.class, 2L);
        var authors2=new ArrayList<Author>();
        authors2.add(author2);
        book.setAuthors(authors2);
        bookRepositoryJpa.save(book);
        bookFromDB = bookRepositoryJpa.findById(book.getId()).get();
        Assertions.assertThat(bookFromDB.getAuthors().get(0).getName()).isEqualTo("Чехов");
    }

    @Test
    @DisplayName("Метод удаления книги по идентификатору работает корректно")
    void deleteTest() {
        var author=em.find(Author.class, 2L);
        var authors=new ArrayList<Author>();
        authors.add(author);
        var genre=em.find(Genre.class, 2L);
        var book1 = bookRepositoryJpa.save(new Book("Книга 13", authors, genre));
        var book2 = bookRepositoryJpa.save(new Book("Книга 14", authors, genre));
        var book3 = bookRepositoryJpa.save(new Book("Книга 15", authors, genre));
        var book4 = bookRepositoryJpa.save(new Book("Книга 16", authors, genre));
        var book5 = bookRepositoryJpa.save(new Book("Книга 17", authors, genre));
        var book6 = bookRepositoryJpa.save(new Book("Книга 18", authors, genre));
        bookRepositoryJpa.deleteById(book5.getId());
        var bookList = bookRepositoryJpa.findAll();
        Assertions.assertThat(bookList.size()).isEqualTo(5);
        Assertions.assertThat(bookList.get(4).getName()).isEqualTo("Книга 18");

    }

}
