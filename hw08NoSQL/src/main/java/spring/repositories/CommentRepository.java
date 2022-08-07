package spring.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import spring.domain.Book;
import spring.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    @Query(value = "{ 'book._id' : ?0 }", fields = "{ 'questions._id' : 0 }")
    List<Comment> findCommentByBookId(String id);
    @Query(value = "{ 'book._id' : ?0 }", delete = true)
    void deleteByBookId(String id);
}
