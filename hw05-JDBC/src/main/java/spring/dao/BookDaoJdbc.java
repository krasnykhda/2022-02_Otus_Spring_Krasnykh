package spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao)
    {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from persons", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Book book) {
        genreDao.insert(book.getGenre());
        authorDao.insert(book.getAuthor());
        namedParameterJdbcOperations.update("insert into book (name,AuthorID,GenreID) values (:name,:authorID,:genreID)",
                Map.of( "name", book.getName(),"authorID",book.getAuthor().getId(),"genreID",book.getGenre().getId()));
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from book where id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name,AuthorID,GenreID from book", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    private  class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("AuthorID");
            long genreID = resultSet.getLong("genreID");

            String name = resultSet.getString("name");
            return new Book(name,authorDao.getById(authorId),genreDao.getById(genreID));
        }
    }
}
