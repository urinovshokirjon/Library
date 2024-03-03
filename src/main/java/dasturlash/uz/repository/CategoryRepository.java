package dasturlash.uz.repository;

import dasturlash.uz.dto.Category;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
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
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;


  public Category getByNameSpring(String name){
      Session session = sessionFactory.openSession();
      Query<Category> query = session.createQuery("FROM Category c where c.name=:name");
      query.setParameter("name",name);
      // bu metod MB borib shunday name category bormi yo'qligini aniqlab bo'lsa olib keladi;
      List<Category> categoryList = query.list();
      if(!categoryList.isEmpty()){
          return categoryList.get(0);
      }
      return null;
  }


    public int saveSpring(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(category);
        transaction.commit();
        session.close();
        return 0;
    }

    public List<Category> getAllSpring(){
      String sql="From Category c where c.visible=true";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(sql);

        List<Category> categoryList = query.list();
        return categoryList;
    }


    public int deleteByIdSpring(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql="Update Category c set c.visible=false where id=:id";
        Query query = session.createQuery(sql);
        query.setParameter("id",id);
      int n=  query.executeUpdate();
      transaction.commit();
        return n;
    }

    public Category getByIdSpring(int id){
        // bu metod MB borib shunday id category bormi yo'qligini aniqlab bo'lsa olib keladi;
        // bu metod BookService classida ishlatiladi
        String sql="from Category c  where c.id=:id";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(sql);
        query.setParameter("id",id);
        List<Category> categoryList = query.list();
        if (categoryList.size()>0){
            return categoryList.get(0);
        }
        return null;


    }

}
