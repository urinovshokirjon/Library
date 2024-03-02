package dasturlash.uz.mappers;

import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import dasturlash.uz.dto.StudentBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBookMapper implements RowMapper<StudentBook> {


    @Override
    public StudentBook mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        StudentBook studentBook = new StudentBook();
        studentBook.setId(resultSet.getInt("id"));
        studentBook.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());

        Book book = new Book();
        book.setId(resultSet.getInt("bookId"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setAvailableDay(resultSet.getInt("available_day"));
        studentBook.setBook(book);

        Category category = new Category();
        category.setId(resultSet.getInt("categoryId"));
        category.setName(resultSet.getString("categoryName"));
        studentBook.setCategory(category);

        return studentBook;
    }
}
