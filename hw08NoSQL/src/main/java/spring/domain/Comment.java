package spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id

    private String id;

    private String name;
    private Book book;


    public Comment(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public Comment(String name) {
        this.name = name;
    }
}
