package dasturlash.uz.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
@Repository
public class TableRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createTables(){
        String profile="create table if not exists profile(" +
                "id serial primary key," +
                "name varchar(25) not null," +
                "surname varchar(25) not null," +
                "login varchar (25) not null unique," +
                "password varchar (35) not null," +
                "phone varchar (12) not null," +
                "status varchar (25) not null," +
                "role varchar (15) not null," +
                "created_date timestamp default now()" +
                ")";
        executeSql(profile);

        String category="create table if not exists category(" +
                "id serial primary key," +
                "name varchar(25) not null unique," +
                "created_date timestamp default now()," +
                "visible boolean default true " +
                ")";
        executeSql(category); // Kitoblarni categoryasi saqlanadigan table yaratiladi

        String book = "create table if not exists book(" +
                "id serial primary key," +
                "title varchar(25) not null," +
                "author varchar(25) not null," + // kitob avtori
                "category_id int not null," +  //category tablega ulangan
                "publish_date date," +          // Chop etilgan sanasi
                "available_day int," +             // Kitobni berish muddati
                "created_date timestamp default now()," +   //Kitobni kutibxonaga kelgan vaqti
                "visible boolean default true, " + //Kitobni muomulada yoki muomulada emasligi
                "constraint fk_category_id foreign key(category_id) references category(id)" +
                ")";
        executeSql(book); // Kitoblarni  saqlanadigan table yaratiladi

                // ProfileCard(id,cardId,profileId,visible,created_date)


        String studentBook="create table if not exists student_book(" +   // Bu tableni vazifasi STUDENT ni book ni oldi berdisini qayt qilishdir;
                " id serial primary key," +
                " student_id int not null," +        // Bu ustun STUDENT tablega bog'lanish uchun;
                " book_id int not null," +           // Bu ustun BOOK tablega bog'lanish uchun. Masalan: manu id li STUDENT manu id BOOK ni oldi yoki berdi deyish uchun;
                " created_date timestamp default now()," + // Manashu vaqtda olgan deyish uchun;
                " deadline_date date," +           // Bu ustun kitob qaytarib berish muddati;
                " returned_date timestamp," +           // Bu ustun kitob qaytarib berilgan san;
                " status varchar(10) not null," +       // Bu ustun kitobni olgan yoki kitobni qaytargan (TAKEN,RETURNED) degan enum tipli status;
                " constraint fk_student_id foreign key(student_id) references profile(id)," + // student_book tabledagi student_id ustun profile tablesidagi kitob olgan studentni id siga bog'langanini bildiradi;
                " constraint fk_book_id foreign key(book_id) references book(id)" +           //student_book tabledagi book_id ustun book tablesidagi qaysidir book ning id siga bog'langanini bildiradi;
                ")";
            executeSql(studentBook);

    }
    public void executeSql(String sql){
        jdbcTemplate.execute(sql);

    }

}
