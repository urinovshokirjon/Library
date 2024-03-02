package dasturlash.uz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionRepository {

 public static Connection getConnection(){

     try {
         return   DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon", "db_user_jon", "12345");
     } catch (SQLException e) {
         e.printStackTrace();
         throw new RuntimeException(e);
     }

 }


}
