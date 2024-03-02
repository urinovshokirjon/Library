package dasturlash.uz.repository;

import dasturlash.uz.dto.StudentBook;
import dasturlash.uz.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class StudentBookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int saveSpring(StudentBook studentBook) {
        String sql = "INSERT INTO student_book(student_id,book_id,created_date,status,deadline_date)" +
                " VALUES (?,?,?,?,?)";
       return jdbcTemplate.update(sql,studentBook.getStudentId(),studentBook.getBookId(),Timestamp.valueOf(studentBook.getCreatedDate()),studentBook.getStatus().name(),Date.valueOf(studentBook.getDeadlineDate()));
    }
    public List<StudentBook> studentBookOnHandSpring(int sId) {
        // Bu metod student olgan kitoblarning ro'yxatini qaytaradi;
        String sql = "select sb.id, sb.created_date," +
                " b.id as bookId, b.title, b.author,b.available_day," +
                " b.category_id as categoryId, c.name as categoryName" +
                " from student_book as sb" +
                " inner join book as b on b.id=sb.book_id" +
                " inner join category as c on c.id=b.category_id" +
                " where status='TAKEN' and student_id=? order by sb.created_date desc;";
        List<StudentBook> studentBooks = jdbcTemplate.query(sql, new StudentBookMapper(), sId);
        return studentBooks;
    }
    public List<StudentBook> takenBookHistorySpring(int sId) {
        // Bu metod student kitob oldi berdisini chiqaradi;
        String sql = "select sb.id, sb.created_date, sb.returned_date," +
                " b.id as book_id, b.title, b.author,b.available_day," +
                " b.category_id as categoryId, c.name as categoryName" +
                " from student_book as sb" +
                " inner join book as b on b.id=sb.book_id" +
                " inner join category as c on c.id=b.category_id" +
                " where student_id=? order by sb.created_date desc;";
        List<StudentBook> studentBooks = jdbcTemplate.query(sql, new StudentTakenBookHistory(), sId);
        return studentBooks;
    }
    public int returnBookSpring(int bId, int sId) {
        // Bu metod student olgan kitoblarning ro'yxatini qaytaradi;
        String sql = "update student_book set status='RETURNED', returned_date=now() " +
                " where student_id=? and book_id=? and status='TAKEN';";
        return jdbcTemplate.update(sql,sId,bId);
    }
    public List<StudentBook> booksOnHandSpring(){
        String sql = "Select  b.id as bookId,b.title,b.author," +
                " b.category_id, c.id as categoryId, c.name as categoryName," +
                " sb.created_date,sb.deadline_date," +
                " p.id as profileId,p.name as profileName,p.surname as profileSurname,p.phone" +
                " from student_book as sb" +
                " inner join book as b on b.id=sb.book_id" +
                " inner join category as c on c.id=b.category_id" +
                " inner join profile as p on p.id=sb.student_id" +
                " where sb.status='TAKEN' order by sb.created_date desc;";
        List<StudentBook> studentBooks = jdbcTemplate.query(sql, new StudentBooksOnHand());
        return studentBooks;
    }
    public List<StudentBook> bookHistorySpring(int bId) {
        String sql ="select sb.created_date,sb.returned_date," +
                " p.id as profileId,p.name as profileName,p.surname as profileSurname,p.phone" +
                " from student_book as sb" +
                " inner join profile as p on p.id=sb.student_id" +
                " where sb.book_id="+bId+" order by sb.created_date desc";
        List<StudentBook> studentBooks = jdbcTemplate.query(sql, new BookHistory());
        return studentBooks;
    }
    public List<StudentBook> bestBooksSpring() {
        String sql ="Select b.id as bookId,b.title,b.author," +
                " c.id as categoryId, c.name as categoryName, temp_t.taken_count" +
                " from (select book_id,count(book_id) as taken_count from student_book " +
                "        group by book_id order by taken_count desc limit 10) as temp_t" +
                " inner join book as b on b.id=temp_t.book_id" +
                " inner join category as c on c.id=b.category_id" +
                " order by taken_count desc";
        List<StudentBook> studentBooks = jdbcTemplate.query(sql, new BestBooks());
        return studentBooks;

    }
}