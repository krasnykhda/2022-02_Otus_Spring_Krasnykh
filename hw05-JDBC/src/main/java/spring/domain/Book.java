package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
public class Book {
    private final Long id;
    private String name;
    private Author author;
    private Genre genre;

    public Book(Long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Author author, Genre genre) {
        this.id = null;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
