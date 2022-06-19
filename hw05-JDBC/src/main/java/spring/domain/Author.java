package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private final Long id;
    private final String name;

    public Author(String name) {
        this.id=null;
        this.name = name;
    }
}
