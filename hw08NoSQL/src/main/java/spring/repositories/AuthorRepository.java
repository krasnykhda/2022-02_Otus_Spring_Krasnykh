package spring.repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import spring.domain.Author;


public interface AuthorRepository extends MongoRepository<Author, String> {



}
