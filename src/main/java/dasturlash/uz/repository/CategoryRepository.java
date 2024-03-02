package dasturlash.uz.repository;

import dasturlash.uz.dto.Category;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
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
    private JdbcTemplate jdbcTemplate;

  public Category getByName(String name){
      // bu metod MB borib shunday name category bormi yo'qligini aniqlab bo'lsa olib keladi;
      Connection connection=ConnectionRepository.getConnection();
      try {
          PreparedStatement preparedStatement = connection.prepareStatement("select * from category where name=?");
          preparedStatement.setString(1,name);
          ResultSet resultSet = preparedStatement.executeQuery();
          if (resultSet.next()){
              Category category=new Category();
              category.setId(resultSet.getInt("id"));
              category.setName(resultSet.getString("name"));
              category.setCreateDate(resultSet.getTimestamp("created_date").toLocalDateTime());
              category.setVisible(resultSet.getBoolean("visible"));
              return category;
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }finally {
          try {
              connection.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
      return null;


  }
  public Category getByNameSpring(String name){
      // bu metod MB borib shunday name category bormi yo'qligini aniqlab bo'lsa olib keladi;
      String sql="select * from category where name=?";
      List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), name);
      if(categoryList.size()>0){
          return categoryList.get(0);
      }
      return null;
  }

    public int save(Category category) {
        Connection connection= ConnectionRepository.getConnection();

        try {
            String sql="INSERT INTO category(name,created_date,visible) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(category.getCreateDate()));
            preparedStatement.setBoolean(3,category.isVisible());
            int natija = preparedStatement.executeUpdate();
            return natija;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }
    public int saveSpring(Category category) {
      String sql="INSERT INTO category(name,created_date,visible) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, category.getName(), category.getCreateDate(), category.isVisible());
    }
    public List<Category> getAll(){
        Connection connection= ConnectionRepository.getConnection();
        List<Category> categoryList=new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * From category where visible=true");
            while (resultSet.next()){
                Category category=new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setCreateDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                category.setVisible(resultSet.getBoolean("visible"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return categoryList;
        }
    public List<Category> getAllSpring(){
      String sql="Select * From category where visible=true";
        List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return categoryList;
    }

    public int deleteById(int id){
        Connection connection= ConnectionRepository.getConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("update category set visible=false where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }
    public int deleteByIdSpring(int id){
      String sql="update category set visible=false where id=" + id;
      return jdbcTemplate.update(sql);
    }
    public Category getById(int id){
        // bu metod MB borib shunday id category bormi yo'qligini aniqlab bo'lsa olib keladi;
        // bu metod BookService classida ishlatiladi
        Connection connection=ConnectionRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Category category=new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setCreateDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                category.setVisible(resultSet.getBoolean("visible"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;


    }
    public Category getByIdSpring(int id){
        // bu metod MB borib shunday id category bormi yo'qligini aniqlab bo'lsa olib keladi;
        // bu metod BookService classida ishlatiladi
        String sql="select * from category where id=?";
        List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class), id);
        if (categoryList.size()>0){
            return categoryList.get(0);
        }
        return null;


    }

}
