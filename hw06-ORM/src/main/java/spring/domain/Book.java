package spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Book(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "GenreID")
    private Genre genre;

    public Book(String name, List<Author> authors, Genre genre) {
        this.name = name;
        this.authors = authors;
        this.genre = genre;
    }


}
