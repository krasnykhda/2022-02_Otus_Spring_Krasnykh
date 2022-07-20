package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Author;
import spring.domain.Genre;
import spring.repositories.AuthorRepository;
import spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }


    @Override
    public Optional<Genre> getById(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);

    }
}
