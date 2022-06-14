package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    private List<Author> authors;
    private Genre genre;
}
