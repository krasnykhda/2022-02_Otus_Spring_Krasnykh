package spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Genre {
    private final Long id;

    public Genre(String name) {
        this.id=null;
        this.name = name;
    }

    private final String name;
}
