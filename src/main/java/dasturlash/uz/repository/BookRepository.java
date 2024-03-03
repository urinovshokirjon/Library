package dasturlash.uz.repository;

import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class BookRepository {
    @Autowired
    private SessionFactory  sessionFactory;

    public int saveSpring(Book book){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();
        session.close();
        return book.getId();
    }

    public List<Book> getAllSpring() {
        Session session = sessionFactory.openSession();
        String sql="From Book b where b.visible=true";
        Query query = session.createQuery(sql);
        List<Book> bookList = query.list();
        session.close();
        return bookList;
    }

    public List<Book> searchSpring(String query) {
        Session session = sessionFactory.openSession();
        String title="%"+query.toLowerCase()+"%";
        Query<Book> query1 = session.createQuery("From Book b where lower(b.title) like :query or lower(b.author) like :query and b.visible=true");
        query1.setParameter("query",title);
        List<Book> bookList = query1.list();
        session.close();
        return bookList;
    }

    public int deleteSpring(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("Update Book b set b.visible=false where id=:id");
        query.setParameter("id",id);
        int n = query.executeUpdate();
        transaction.commit();
        session.close();
        return n;
    }


    public Book getBookByIdSpring(Integer id){
        // Bu metod shudan id book bor yo'qligini aniqlaydi;
        Session session = sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM Book b WHERE b.id=:id");
        query.setParameter("id",id);
        List<Book> bookList = query.list();
        if(!bookList.isEmpty()){
            return bookList.get(0);
        }
        else{
            return null;
        }
    }

    public List<Book> getAllByCategoryIdSpring(int categoryId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("From Book b where b.category.id=:id");
        query.setParameter("id",categoryId);
        List<Book> bookList = query.list();
        return bookList;
    }
}
