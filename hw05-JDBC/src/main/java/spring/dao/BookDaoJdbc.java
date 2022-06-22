package spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
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
        var genre = genreDao.insert(book.getGenre());
        var author = authorDao.insert(book.getAuthor());
        paramSource.addValue("name", book.getName());
        paramSource.addValue("authorID", author.getId());
        paramSource.addValue("genreID", genre.getId());
        namedParameterJdbcOperations.update("insert into book (name,AuthorID,GenreID) values (:name,:authorID,:genreID)",
                paramSource, keyHolder);
        long newId = 0;
        if (keyHolder.getKeys().size() > 1) {
            newId = (Long) keyHolder.getKeys().get("id");
        } else {
            newId = keyHolder.getKey().longValue();
        }
        return new Book(newId, book.getName(), author, genre);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name,AuthorID,genreID from book where id = :id", params, new BookMapper(authorDao,genreDao));
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name,AuthorID,GenreID from book", new BookMapper(authorDao,genreDao));
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
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("AuthorID");
            long genreID = resultSet.getLong("GenreID");

            String name = resultSet.getString("name");
            return new Book(id, name, authorDao.getById(authorId), genreDao.getById(genreID));
        }
    }
}
