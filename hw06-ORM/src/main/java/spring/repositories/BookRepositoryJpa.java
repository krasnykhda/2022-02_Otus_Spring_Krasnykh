package spring.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public class BookRepositoryJpa implements BookRepository {


    @PersistenceContext
    private final EntityManager em;


    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }

    }

    @Override
    public Optional<Book> findById(long id) {

        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select s from Book s join fetch s.genre", Book.class);
        var resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select s " +
                        "from Book s " +
                        "where s.name = :name",
                Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Book s " +
                "set s.name = :name " +
                "where s.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
