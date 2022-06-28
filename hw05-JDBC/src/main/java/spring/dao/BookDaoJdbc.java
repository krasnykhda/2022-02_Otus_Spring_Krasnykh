package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import spring.domain.Author;
import spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }


    @Override
    public Book insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("name", book.getName());
        paramSource.addValue("authorID", book.getAuthor().getId());
        paramSource.addValue("genreID", book.getGenre().getId());
        namedParameterJdbcOperations.update("insert into book (name,AuthorID,GenreID) values (:name,:authorID,:genreID)",
                paramSource, keyHolder, new String[]{"id"});
        long newId = keyHolder.getKey().longValue();

        return new Book(newId, book.getName(), book.getAuthor(), book.getGenre());
    }

    @Override
    public Book update(Book book) {
        namedParameterJdbcOperations.update("update book set name=:name,AuthorID=:authorID,GenreID=:genreID  where id =:id",
                Map.of("name", book.getName(), "authorID", book.getAuthor().getId(), "genreID", book.getGenre().getId(), "id", book.getId()));
        return book;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name,AuthorID,genreID from book where id = :id", params, new BookMapper(authorDao, genreDao));
    }

    @Override
    public List<Book> getAll() {
        //return jdbc.query("select id, name,AuthorID,GenreID from book", new BookMapper(authorDao, genreDao));
        return jdbc.query("select b.id as bookId, b.name as bookName,b.AuthorID,b.GenreID,a.name as authorName " +
                "from book b left join author a on b.id=a.id", new BookMapper(authorDao, genreDao));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from book where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final GenreDao genreDao;

        public BookMapper(AuthorDao authorDao, GenreDao genreDao) {
            this.authorDao = authorDao;
            this.genreDao = genreDao;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("bookId");
            long authorId = resultSet.getLong("AuthorID");
            long genreID = resultSet.getLong("GenreID");
            String authorName=resultSet.getString("authorName");
            String name = resultSet.getString("bookName");
            return new Book(id, name, new Author(authorId,authorName), genreDao.getById(genreID));
        }
    }
}
