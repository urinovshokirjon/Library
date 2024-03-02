package dasturlash.uz.repository;


import dasturlash.uz.mappers.ProfileMapper;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProfileRepository {
    // Bu class Profile table bilan ishlaydigan class bo'ladi MB borib ma'lumotlarni yozadi va o'qib keladi
    @Autowired
    private SessionFactory sessionFactory;

    public Profile getByLoginSpring(String login) {
        // Bu metod shudan loginli profile bor yo'qligini aniqlaydi;
        try {
            Session session = sessionFactory.openSession();
            Query<Profile> query = session.createQuery("From Profile p where p.login=:login");
            query.setParameter("login",login);
            List<Profile> list = query.list();
            return list.get(0);
        } catch (Exception e) {

        }
        return null;
    }

    public int createSpring(Profile profile) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(profile);
        transaction.commit();
        session.close();
        return 0;
    }

    public List<Profile> getAllSpring(ProfileRoles... roles) {

        Session session = sessionFactory.openSession();

        Query<Profile> query = session.createQuery("From Profile p where (p.roles in :roles) order by p.createdDate");
        query.setParameter("roles", Arrays.asList(roles));


        List<Profile> profileList = query.list();
        return profileList;
    }

    public List<Profile> searchStudentSpring(String query) {
        String param = "%" + query.toLowerCase() + "%";
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("FROM Profile p where p.roles=:roles and LOWER(p.name) like :query or LOWER(p.surname) like :query or LOWER(p.phone) like :query");
        query1.setParameter("roles",ProfileRoles.STUDENT);
        query1.setParameter("query",param);
        List<Profile> profileList = query1.list();
        return profileList;
    }

    public List<Profile> searchProfileSpring(String query) {
        String param = "%" + query.toLowerCase() + "%";
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("FROM Profile p where p.roles!=:roles and LOWER(p.name) like :query or LOWER(p.surname) like :query or LOWER(p.phone) like :query");
        query1.setParameter("roles",ProfileRoles.STUDENT);
        query1.setParameter("query",param);
        List<Profile> profileList = query1.list();
        return profileList;

    }

    public Profile getByIdSpring(Integer id) {
        // Bu metod shudan id profile bor yo'qligini aniqlaydi;
        String sql = "FROM Profile p WHERE p.id=" + id;
        Session session = sessionFactory.openSession();
        Query<Profile> query = session.createQuery(sql);
        List<Profile> profileList = query.list();
        if (profileList.size() > 0) {
            return profileList.get(0);
        }
        return null;
    }

    public int updateStatusSpring(int id, ProfileStatus status) {
        String sql = "UPDATE profile SET status=? WHERE id=?";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Profile p SET p.status=:status WHERE p.id=:id");
        query.setParameter("status",status);
        query.setParameter("id", id);
        int n=query.executeUpdate();
        transaction.commit();
        session.close();
        return n;
    }

}
