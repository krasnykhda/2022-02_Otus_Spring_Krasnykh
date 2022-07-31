package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;

import java.util.List;

@Service
public class BookToStringConverter {
    public String getBookAsString(Book book) {
        StringBuilder builder = new StringBuilder("\n").append("id книги: ")
        .append(book.getId())
        .append("\n")
        .append(book.getName())
        .append("\n")
        .append("Жанр книги: ")
        .append(book.getGenre().getName())
        .append("\n")
        .append("Авторы книги: [")
        .append("\n");
        for (Author author : book.getAuthors()) {
            builder.append("   Имя автора: ")
            .append(author.getName());
        }
        builder.append("\n")
        .append("]");

        return builder.toString();
    }


    public String getBookAsString(List<Book> books) {
        StringBuilder builder = new StringBuilder("\n");
        for (Book book : books) {
            builder.append(getBookAsString(book));
            builder.append("\n");
        }
        return builder.toString();
    }
}
