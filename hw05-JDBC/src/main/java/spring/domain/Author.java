package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class Author {
    private final Long id;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String name;

    public Author(String name) {
        this.id = null;
        this.name = name;
    }
}
