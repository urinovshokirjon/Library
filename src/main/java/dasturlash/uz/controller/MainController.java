package dasturlash.uz.controller;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.repository.TableRepository;
import dasturlash.uz.sevice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private TableRepository tableRepository;
    private  InitServes initServes;
    private BookService bookService;
    private AuthService authServes;
    private CategoryService categoryService;
    private ScannerService scannerService;

    public void start() {
        tableRepository.createTables();
        initServes.initAmin();
        initServes.initTestStudent();
        boolean b = true;
        while (b) {
            System.out.println("Alloh ilmimizni ziyoda qilsin!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                    bookService.all();
                    break;
                }
                case 2: {
                    search();
                    break;
                }
                case 3: {
                    byCategory();
                    break;
                }
                case 4: {
                    login();
                    break;
                }
                case 5: {
                    registration();
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
        System.out.println("*** Main Menu ***");
        System.out.println("1. BookList");
        System.out.println("2. Search");
        System.out.println("3. By category");
        System.out.println("4. Login");
        System.out.println("5. Registration");
        System.out.println("0. Exit");
    }

    public void login() {
        System.out.print("Enter login: ");
        String login = scannerService.getStrscanner().next();

        System.out.print("Enter password: ");
        String password = scannerService.getStrscanner().next();

       authServes.login(login, password);

    }
    public void registration() {
        System.out.print("Enter name: ");
        String name = scannerService.getStrscanner().next();

        System.out.print("Enter surname: ");
        String surname = scannerService.getStrscanner().next();

        System.out.print("Enter phone: ");
        String phone = scannerService.getStrscanner().next();

        System.out.print("Enter login: ");
        String login = scannerService.getStrscanner().next();

        System.out.print("Enter password: ");
        String password = scannerService.getStrscanner().next();

        Profile profile = new Profile();
        profile.setName(name.trim());
        profile.setSurname(surname.trim());
        profile.setPhone(phone.trim());
        profile.setLogin(login.trim());
        profile.setPassword(password);

       authServes.registration(profile);

    }
    public void search(){
        System.out.print("Enter query: ");
        String query=scannerService.getStrscanner().next();
        bookService.search(query);
    }
    public void byCategory(){
        categoryService.list();
        System.out.print("Enter categoryId: ");
        int categoryId=scannerService.getIntscanner().nextInt();
        bookService.byCategoryId(categoryId);
    }

    @Autowired
    public MainController(TableRepository tableRepository, InitServes initServes, BookService bookService, AuthService authServes, CategoryService categoryService, ScannerService scannerService) {
        this.tableRepository = tableRepository;
        this.initServes = initServes;
        this.bookService = bookService;
        this.authServes = authServes;
        this.categoryService = categoryService;
        this.scannerService = scannerService;
    }
}
