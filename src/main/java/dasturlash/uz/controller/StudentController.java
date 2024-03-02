package dasturlash.uz.controller;

import dasturlash.uz.sevice.BookService;
import dasturlash.uz.sevice.ScannerService;
import dasturlash.uz.sevice.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    private StudentBookService studentBookService;
    private BookService bookService;
    private ScannerService scannerService;
    public void start() {
        System.out.println("Xurmatli talabalar olgan kitoblaringizni o'z vaqtida qaytaring!");
        studentBookService.booksOnHandByStudentId();
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Student Menu ga ham o'tib oldik!");
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
                    takeBook();
                    break;
                }
                case 4: {
                    returnBook();
                    break;
                }
                case 5: {
                   studentBookService.booksOnHandByStudentId();
                    break;
                }
                case 6: {
                    studentBookService.takenBookHistory();
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
        System.out.println("*** Student Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. Take book");     // Student kitobni olish
        System.out.println("4. Return book");   // Studentni olgan Kitobni qaytarish
        System.out.println("5. Books on hand"); // Studentni olgan kitoblari ko'rish
        System.out.println("6. Take book history"); //Studentni olgan kitoblarini tarixini ko'rish
        System.out.println("0. Exit");
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
    public void takeBook(){
        System.out.print("Enter book id: ");
        int bId=scannerService.getIntscanner().nextInt();
        studentBookService.takeBook(bId);
    }

    public void returnBook(){
        System.out.print("Enter book id: ");
        int bId=scannerService.getIntscanner().nextInt();
        studentBookService.returnBook(bId);
    }

    @Autowired
    public StudentController(StudentBookService studentBookService, BookService bookService, ScannerService scannerService) {
        this.studentBookService = studentBookService;
        this.bookService = bookService;
        this.scannerService = scannerService;
    }
}
