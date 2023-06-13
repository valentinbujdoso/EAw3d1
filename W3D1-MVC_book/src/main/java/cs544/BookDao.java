package cs544;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao implements IBookDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAll() {
        return em.createQuery("from Book", Book.class).getResultList();
    }

    @Override
    public void add(Book book) {
        em.persist(book);
    }

    @Override
    public Book get(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void delete(int bookId) {
        Book b = em.getReference(Book.class, bookId);
        em.remove(b);
    }
}
