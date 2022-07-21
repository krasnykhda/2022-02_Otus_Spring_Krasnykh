package spring.service;

import org.springframework.stereotype.Service;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;

@Service
public class CommentToStringConverter {
    public String getCommentAsString(List<Comment> comments) {
        StringBuilder builder = new StringBuilder("\n");
        for (Comment comment : comments) {
            builder.append("id комментария: ");
            builder.append(comment.getId());
            builder.append("\n");
            builder.append("Текст комментария: ");
            builder.append(comment.getName());
            builder.append("\n");
        }
        return builder.toString();
    }
}
