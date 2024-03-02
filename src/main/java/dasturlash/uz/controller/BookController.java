package dasturlash.uz.controller;

import dasturlash.uz.dto.Book;
import dasturlash.uz.sevice.BookService;
import dasturlash.uz.sevice.ScannerService;
import dasturlash.uz.sevice.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
@Controller
public class BookController {

    private BookService bookService;
    private StudentBookService studentBookService;
    private ScannerService scannerService;
    public void start() {
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Book Menu ga ham o'tib oldik!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                    list();
                    break;
                }
                case 2: {
                    search();
                    break;
                }
                case 3: {
                    add();
                    break;
                }
                case 4: {
                    delete();
                    break;
                }
                case 5: {
                   studentBookService.booksOnHand();
                    break;
                }
                case 6: {
                    bookHistory();
                    break;
                }
                case 7: {
                   studentBookService.bestBooks();
                    break;
                }
                case 0: {
                    b = false;
                    break;
                }
                default: {
                    System.out.println("Adashtingiz bunaqa komanda yo'q");
                }
            }

        }
    }
    public void showMenu() {
        System.out.println("*** Book Menu ***");
        System.out.println("1. Book list");
        System.out.println("2. Search");
        System.out.println("3. Add book");
        System.out.println("4. Remove book");
        System.out.println("5. Book on hand");
        System.out.println("6. Book history");
        System.out.println("7. Best books");
        System.out.println("0. Exit");
    }

    public void add(){
        System.out.print("Enter title: ");
        String title=scannerService.getStrscanner().next();

        System.out.print("Enter author: ");
        String author=scannerService.getStrscanner().next();

        System.out.print("Enter category id: ");
        Integer categoryId=scannerService.getIntscanner().nextInt();

        System.out.print("Enter available day: ");
        Integer availableDay=scannerService.getIntscanner().nextInt();

        System.out.print("Enter publish date (yyyy-MM-dd): ");//2024-01-15
        String publishDate=scannerService.getStrscanner().next();

        Book book=new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailableDay(availableDay);
        book.setPublishDate(LocalDate.parse(publishDate));

       bookService.add(categoryId,book);
    }
    private void list() {
        System.out.println("---- Book list ----");
        bookService.all();
    }
    public void search(){
        System.out.print("Enter query: ");
        String query=scannerService.getStrscanner().next();
       bookService.search(query);
    }
    public void delete(){
        System.out.print("Enter book id: ");
        int bookId=scannerService.getIntscanner().nextInt();
        bookService.delete(bookId);
    }
    public void bookHistory(){
        System.out.print("Enter book id: ");
        int bookId=scannerService.getIntscanner().nextInt();
       studentBookService.bookHistory(bookId);
    }
    @Autowired
    public BookController(BookService bookService, StudentBookService studentBookService, ScannerService scannerService) {
        this.bookService = bookService;
        this.studentBookService = studentBookService;
        this.scannerService = scannerService;
    }
}
