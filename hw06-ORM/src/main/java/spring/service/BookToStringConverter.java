package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;

import java.util.List;

@Service
public class BookToStringConverter {
    public String getBookAsString(Book book){
        StringBuilder builder = new StringBuilder("\n");
        builder.append("id книги: ");
        builder.append(book.getId());
        builder.append("\n");
        builder.append("Название книги: ");
        builder.append(book.getName());
        builder.append("\n");
        builder.append("Жанр книги: ");
        builder.append(book.getGenre().getName());
        builder.append("\n");
        builder.append("Авторы книги: [");
        builder.append("\n");
        for (Author author: book.getAuthors()){
            builder.append("   Имя автора: ");
            builder.append(author.getName());
        }
        builder.append("\n");
        builder.append("]");

        return builder.toString();
    }



    public String getBookAsString(List<Book> books){
        var booksString="";
        for (Book book:books){
            booksString=booksString+ getBookAsString(book)+"\n";
        }
        return booksString;
    }
}
