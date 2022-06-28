package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.GenreDao;
import spring.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre insert(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreDao.update(genre);
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public void deleteById(long id) {
        genreDao.deleteById(id);

    }
}
