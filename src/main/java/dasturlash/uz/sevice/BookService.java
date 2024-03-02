package dasturlash.uz.sevice;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Book;
import dasturlash.uz.dto.Category;
import dasturlash.uz.repository.BookRepository;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BookService {
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;



    public void add(Book book) {
       if (!isValid(book)){
           return;
       }
        Category category=categoryRepository.getByIdSpring(book.getCategoryId());
         if(category==null){
             System.out.println("Category not found");
             return;
         }
         book.setCreatedDate(LocalDateTime.now());
         book.setVisible(true);
        int effectedRow = bookRepository.saveSpring(book);
        if (effectedRow==1){
            System.out.println("Book saved.");
        }else {
            System.out.println("Book not saved");
        }

    }

    public boolean isValid(Book book){
        if (book.getTitle()==null || book.getTitle().isBlank() || book.getTitle().length()<3){
            System.out.println("Title noto'g'ri");
            return false;
        }
        if (book.getAuthor()==null || book.getAuthor().isBlank() || book.getAuthor().length()<3){
            System.out.println("Author noto'g'ri");
            return false;
        }
        if (book.getAvailableDay()==null || book.getAvailableDay()<=0){
            System.out.println("Available day noto'g'ri");
        }
        return true;

    }

    public void all(){
        List<Book> bookList=bookRepository.getAllSpring();
        bookList.forEach(book -> {
            System.out.println(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+book.getCategoryName());
        });
    }
    public void search(String query){
        List<Book> bookList=bookRepository.searchSpring(query);
        bookList.forEach(book -> {
            System.out.println(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+book.getCategoryName());
        });
    }

    public void delete(int bookId) {
        int effectedRow = bookRepository.deleteSpring(bookId);
        if (effectedRow==1){
            System.out.println("Book delete");
        }else {
            System.out.println("Book not delete");
        }
    }

    public void byCategoryId(int categoryId) {
        List<Book> bookList=bookRepository.getAllByCategoryIdSpring(categoryId);
        bookList.forEach(book -> {
            System.out.println(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+book.getCategoryName());
        });
    }
    @Autowired
    public BookService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }
}
