package dasturlash.uz.repository;


import dasturlash.uz.mappers.ProfileMapper;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ProfileRepository {
    // Bu class Profile table bilan ishlaydigan class bo'ladi MB borib ma'lumotlarni yozadi va o'qib keladi
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Profile getByLoginSpring(String login) {
        // Bu metod shudan loginli profile bor yo'qligini aniqlaydi;
        String sql = "SELECT * FROM profile WHERE login=?";
        List<Profile> profileList = jdbcTemplate.query(sql, new ProfileMapper(), login);
        if (profileList.size() > 0) {
            return profileList.get(0);
        } else {
            return null;
        }
    }

    public int createSpring(Profile profile) {
        String sql = "INSERT INTO profile(name,surname,login,password,phone,status,role,created_date) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, profile.getName(), profile.getSurname(), profile.getLogin(), profile.getPassword(), profile.getPhone(), String.valueOf(profile.getStatus()), String.valueOf(profile.getRoles()), Timestamp.valueOf(profile.getCreatedDate()));
    }

    public List<Profile> getAllSpring(ProfileRoles... roles) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (ProfileRoles role : roles) {
            sb.append("'").append(role.name()).append("'");
            if (roles.length - 1 != count) {
                sb.append(",");
            }
            count++;
        }
        String sql = "Select * From profile where role in (%s) order by created_date";
        sql = String.format(sql, sb.toString());
        List<Profile> profileList = jdbcTemplate.query(sql, new ProfileMapper());
        return profileList;
    }

    public List<Profile> searchStudentSpring(String query) {
        String sql = "Select * From profile where role ='STUDENT' and" +
                " (lower(name) like ? or lower(surname) like ? or lower(login) like ? or lower(phone) like ?) ";
        String param = "%" + query.toLowerCase() + "%";
        List<Profile> profileList = jdbcTemplate.query(sql, new ProfileMapper(), param, param, param, param);
        return profileList;
    }

    public List<Profile> searchProfileSpring(String query) {
        String sql = "Select * From profile where role !='STUDENT' and" +
                " (lower(name) like ? or lower(surname) like ? or lower(login) like ? or lower(phone) like ?) ";
        String param = "%" + query.toLowerCase() + "%";
        List<Profile> profileList = jdbcTemplate.query(sql, new ProfileMapper(), param, param, param, param);
        return profileList;

    }

    public Profile getByIdSpring(Integer id) {
        // Bu metod shudan id profile bor yo'qligini aniqlaydi;
        String sql="SELECT * FROM profile WHERE id=?";
        List<Profile> profileList = jdbcTemplate.query(sql, new ProfileMapper(), id);
        if (profileList.size()>0){
            return profileList.get(0);
        }
        return null;
    }

    public int updateStatusSpring(int id, ProfileStatus status) {
        String sql = "UPDATE profile SET status=? WHERE id=?";
        return jdbcTemplate.update(sql,status.name(),id);
    }
}
