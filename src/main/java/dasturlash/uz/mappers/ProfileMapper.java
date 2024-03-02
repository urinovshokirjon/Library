package dasturlash.uz.mappers;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements RowMapper<Profile> {

    @Override
    public Profile mapRow(ResultSet rs, int rowNumber) throws SQLException {

        Profile profile = new Profile();
        profile.setId(rs.getInt("id"));
        profile.setName(rs.getString("name"));
        profile.setSurname(rs.getString("surname"));
        profile.setLogin(rs.getString("login"));
        profile.setPassword(rs.getString("password"));
        profile.setPhone(rs.getString("phone"));
        profile.setRoles(ProfileRoles.valueOf(rs.getString("role")));
        profile.setStatus(ProfileStatus.valueOf(rs.getString("status")));
        profile.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
        return profile;
    }
}
