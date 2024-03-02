package dasturlash.uz.repository;

import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Book book){
        Connection connection=ConnectionRepository.getConnection();
        try {
            String sql="INSERT INTO book(title,author,category_id,publish_date,available_day,created_date,visible)" +
                    " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3,book.getCategoryId());
            preparedStatement.setDate(4, Date.valueOf(book.getPublishDate()));
            preparedStatement.setInt(5,book.getAvailableDay());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(book.getCreatedDate()));
            preparedStatement.setBoolean(7,book.getVisible());

            int effectodRow = preparedStatement.executeUpdate();
            return effectodRow;
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
    public int saveSpring(Book book){
        String sql="INSERT INTO book(title,author,category_id,publish_date,available_day,created_date,visible)" +
                " VALUES (?,?,?,?,?,?,?)";
        int effectedRow = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategoryId(), book.getPublishDate(), book.getAvailableDay(), book.getCreatedDate(), book.getVisible());
        return effectedRow;
    }

    public List<Book> getAll() {
        Connection connection = ConnectionRepository.getConnection();
        List<Book> bookList = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT b.id,b.title,b.author,b.publish_date,b.available_day,b.created_date,c.name as category_name FROM book as b " +
                    " inner join category as c on c.id=b.category_id where b.visible=true order by b.id asc");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(resultSet.getDate("publish_date").toLocalDate());
                book.setAvailableDay(resultSet.getInt("available_day"));
                book.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                book.setCategoryName(resultSet.getString("category_name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookList;
    }
    public List<Book> getAllSpring() {
        String sql="SELECT b.id,b.title,b.author,b.publish_date,b.available_day,b.created_date,c.name as category_name FROM book as b " +
                " inner join category as c on c.id=b.category_id where b.visible=true order by b.id asc";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return bookList;
    }
    public List<Book> search(String query) {
        Connection connection = ConnectionRepository.getConnection();
        List<Book> bookList = new LinkedList<>();
        try {
            String sql="SELECT b.*,c.name as category_name FROM book as b" +
                    " inner join category as c on c.id=b.category_id" +
                    " where lower (title) like ? or lower(author) like ? and b.visible=true" +
                    " order by b.id asc";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String title="%"+query.toLowerCase()+"%";
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(resultSet.getDate("publish_date").toLocalDate());
                book.setAvailableDay(resultSet.getInt("available_day"));
                book.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                book.setCategoryName(resultSet.getString("category_name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookList;
    }
    public List<Book> searchSpring(String query) {
        String sql="SELECT b.*,c.name as category_name FROM book as b" +
                " inner join category as c on c.id=b.category_id" +
                " where lower (title) like ? or lower(author) like ? and b.visible=true" +
                " order by b.id asc";
        String title="%"+query.toLowerCase()+"%";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), title,title);
        return bookList;
    }

    public int delete(int bookId) {
        Connection connection= ConnectionRepository.getConnection();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate("update book set visible=false where id=" + bookId);
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
    public int deleteSpring(int bookId) {
        String sql="update book set visible=false where id=" + bookId;
        return jdbcTemplate.update(sql);
    }

    public Book getBookById(Integer id){
        // Bu metod shudan id book bor yo'qligini aniqlaydi;

        Connection connection= ConnectionRepository.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublishDate(resultSet.getDate("publish_date").toLocalDate());
                book.setAvailableDay(resultSet.getInt("available_day"));
                book.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                book.setCategoryId(resultSet.getInt("category_id"));
                return book;
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
    public Book getBookByIdSpring(Integer id){
        // Bu metod shudan id book bor yo'qligini aniqlaydi;
        String sql="SELECT * FROM book WHERE id=?";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id);
        if(bookList.size()>0){
            return bookList.get(0);
        }
        else{
            return null;
        }
    }

    public List<Book> getAllByCategoryId(int categoryId) {
        Connection connection=ConnectionRepository.getConnection();
        List<Book> bookList=new LinkedList<>();

        try {
            String sql="SELECT b.id,b.title,b.author,b.category_id,c.name as categoryName " +
                    " FROM book as b " +
                    " inner join category as c on c.id=b.category_id " +
                    " where b.visible=true and b.category_id=? order by b.id asc;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Book book=new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setCategoryId(resultSet.getInt("category_id"));
                book.setCategoryName(resultSet.getString("categoryName"));
                bookList.add(book);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return bookList;

    }
    public List<Book> getAllByCategoryIdSpring(int categoryId) {
        String sql="SELECT b.id,b.title,b.author,b.category_id,c.name as categoryName " +
                " FROM book as b " +
                " inner join category as c on c.id=b.category_id " +
                " where b.visible=true and b.category_id=? order by b.id asc;";
        List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), categoryId);
        return bookList;
    }
}
