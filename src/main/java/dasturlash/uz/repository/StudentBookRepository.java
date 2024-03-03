package dasturlash.uz.repository;

import dasturlash.uz.dto.BestStudentBook;
import dasturlash.uz.dto.StudentBook;
import dasturlash.uz.enums.StudentBookStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentBookRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public int saveSpring(StudentBook studentBook) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(studentBook);
        transaction.commit();
        return 1;
    }

    public List<StudentBook> studentBookOnHandSpring(int sId) {
        // Bu metod student olgan kitoblarning ro'yxatini qaytaradi;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("From StudentBook sb where sb.status=:status and sb.student.id=:id");
        query.setParameter("status", StudentBookStatus.TAKEN);
        query.setParameter("id", sId);
        List<StudentBook> studentBooks = query.list();
        return studentBooks;
    }

    public List<StudentBook> takenBookHistorySpring(int sId) {
        // Bu metod student kitob oldi berdisini chiqaradi;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("From StudentBook sb where sb.student.id=:sId");
        query.setParameter("sId",sId);
        List<StudentBook> studentBooks = query.list();
        return studentBooks;
    }

    public int returnBookSpring(int bId, int sId) {
        // Bu metod student olgan kitoblarning ro'yxatini qaytaradi;
        String sql = "update StudentBook sb set sb.status=:returnStatus, sb.returnedDate=now() " +
                " where sb.student.id=:sId and sb.book.id=:bId and sb.status=:takenStatus";
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<StudentBook> query = session.createQuery(sql);
        query.setParameter("returnStatus", StudentBookStatus.RETURNED);
        query.setParameter("sId", sId);
        query.setParameter("bId", bId);
        query.setParameter("takenStatus", StudentBookStatus.TAKEN);
        int n = query.executeUpdate();
        transaction.commit();
        return n;
    }

    public List<StudentBook> booksOnHandSpring() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("From StudentBook sb where sb.status=:status");
        query.setParameter("status",StudentBookStatus.TAKEN);
        List<StudentBook> studentBooks = query.list();
        return studentBooks;
    }

    public List<StudentBook> bookHistorySpring(int bId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("From StudentBook sb where sb.book.id=:id ");
        query.setParameter("id",bId);
        List<StudentBook> studentBooks = query.list();
        return studentBooks;
    }

    public List<BestStudentBook> bestBooksSpring() {
        String sql = "Select b.id as bookId,b.title,b.author," +
                " c.id as categoryId, c.name as categoryName, temp_t.taken_count" +
                " from (select book_id,count(book_id) as taken_count from student_book " +
                " group by book_id order by taken_count desc limit 10) as temp_t" +
                " inner join book as b on b.id=temp_t.book_id" +
                " inner join category as c on c.id=b.category_id" +
                " order by taken_count desc";
        Session session = sessionFactory.openSession();
        Query<BestStudentBook> query = session.createQuery("Select new BestStudentBook (sb,co) count(sb.book.id) co from StudentBook as sb group by sb.book.id");
        List<BestStudentBook> studentBooks = query.list();
        return studentBooks;

    }
}