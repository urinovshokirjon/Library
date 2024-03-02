package dasturlash.uz.mappers;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.dto.StudentBook;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookHistory implements RowMapper<StudentBook> {
    @Override
    public StudentBook mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        StudentBook studentBook = new StudentBook();
        studentBook.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        Timestamp returnedDate = resultSet.getTimestamp("returned_date");
        if (returnedDate !=null){
            studentBook.setReturnedDate(returnedDate.toLocalDateTime());
        }
        Profile profile=new Profile();
        profile.setId(resultSet.getInt("profileId"));
        profile.setName(resultSet.getString("profileName"));
        profile.setSurname(resultSet.getString("profileSurname"));
        profile.setPhone(resultSet.getString("phone"));
        studentBook.setStudent(profile);

        return studentBook;
    }
}
