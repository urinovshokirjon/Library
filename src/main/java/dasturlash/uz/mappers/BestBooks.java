package dasturlash.uz.mappers;

import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import dasturlash.uz.dto.StudentBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BestBooks implements RowMapper<StudentBook> {
    @Override
    public StudentBook mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        StudentBook studentBook = new StudentBook();
        studentBook.setTakenCount(resultSet.getInt("taken_count"));

        Book book = new Book();
        book.setId(resultSet.getInt("bookId"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        studentBook.setBook(book);

        Category category = new Category();
        category.setId(resultSet.getInt("categoryId"));
        category.setName(resultSet.getString("categoryName"));
        studentBook.setCategory(category);
        return studentBook;
    }
}
