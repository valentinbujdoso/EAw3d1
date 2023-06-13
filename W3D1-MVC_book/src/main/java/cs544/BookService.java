package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private  IBookDao bookDao;

    public List<Book> getAll() {
        return bookDao.findAll();
    }

    public void add(Book book) {
        bookDao.save(book);
    }

    public Book get(int id) {
//        return bookDao.findById(id).get();
        return bookDao.getById(id);
    }

    public void update(Book book) {
        bookDao.save(book);
    }

    public void delete(int id) {
        bookDao.delete(this.get(id));
    }
}
