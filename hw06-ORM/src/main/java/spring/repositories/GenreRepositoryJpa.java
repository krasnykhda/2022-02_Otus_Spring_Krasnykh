package spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Author;
import spring.domain.Book;
import spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class GenreRepositoryJpa implements GenreRepository {


    @PersistenceContext
    private final EntityManager em;


    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }

    }

    @Override
    public Genre findById(long id) {

        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select s from Genre s", Genre.class);
        var resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = em.createQuery("select s " +
                        "from Genre s " +
                        "where s.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Genre s " +
                "set s.name = :name " +
                "where s.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        em.remove(em.find(Genre.class,id));
    }

}
