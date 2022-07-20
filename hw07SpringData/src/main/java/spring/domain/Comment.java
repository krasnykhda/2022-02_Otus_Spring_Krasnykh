package spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {
    @Id // Позволяет указать какое поле является идентификатором
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации идентификаторов
    private long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BookID")
    private Book book;

    public Comment(String name, Book book) {
        this.name = name;
        this.book = book;
    }

    public Comment(String name) {
        this.name = name;
    }
}
