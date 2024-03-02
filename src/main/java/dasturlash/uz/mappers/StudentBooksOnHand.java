package dasturlash.uz.mappers;

import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.dto.StudentBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBooksOnHand implements RowMapper<StudentBook> {
    @Override
    public StudentBook mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        StudentBook studentBook = new StudentBook();
        studentBook.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        studentBook.setDeadlineDate(resultSet.getDate("deadline_date").toLocalDate());

        Book book = new Book();
        book.setId(resultSet.getInt("bookId"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        studentBook.setBook(book);

        Category category = new Category();
        category.setId(resultSet.getInt("categoryId"));
        category.setName(resultSet.getString("categoryName"));
        studentBook.setCategory(category);

        Profile profile=new Profile();
        profile.setId(resultSet.getInt("profileId"));
        profile.setName(resultSet.getString("profileName"));
        profile.setSurname(resultSet.getString("profileSurname"));
        profile.setPhone(resultSet.getString("phone"));
        studentBook.setStudent(profile);

        return studentBook;
    }
}
