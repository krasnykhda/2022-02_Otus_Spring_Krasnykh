package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class Genre {
    private final Long id;


    private final String name;
    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.id = null;
        this.name = name;
    }
}
