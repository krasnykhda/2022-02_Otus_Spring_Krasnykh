package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Book {
    private final Long id;
    private final String name;
    private Author author;
    private Genre genre;

    public Book(String name, Author author, Genre genre) {
        this.id=null;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}