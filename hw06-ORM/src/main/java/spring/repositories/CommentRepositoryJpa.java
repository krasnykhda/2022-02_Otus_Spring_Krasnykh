package spring.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.domain.Book;
import spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class CommentRepositoryJpa implements CommentRepository {


    @PersistenceContext
    private final EntityManager em;


    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }

    }

    @Override
    public Optional<Comment> findById(long id) {

        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select s from Comment s", Comment.class);
        var resultList = query.getResultList();
        return resultList;

    }

    @Override
    public List<Comment> findByName(String name) {
        TypedQuery<Comment> query = em.createQuery("select s " +
                        "from Comment s " +
                        "where s.name = :name",
                Comment.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update Comment s " +
                "set s.name = :name " +
                "where s.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Comment s " +
                "where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
