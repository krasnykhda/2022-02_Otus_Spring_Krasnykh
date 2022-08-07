package spring.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import spring.domain.Genre;


public interface GenreRepository extends MongoRepository<Genre, String> {


}