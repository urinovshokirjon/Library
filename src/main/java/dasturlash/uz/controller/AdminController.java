package dasturlash.uz.controller;

import dasturlash.uz.sevice.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    private BookController bookController;
    private CategoryController categoryController;
    private StudentProfileController studentProfileController;
    private ProfileController profileController;
    private ScannerService scannerService;


    public void start() {
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Admin Menu ga ham o'tib oldik!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                   bookController.start();
                    break;
                }
                case 2: {
                    categoryController.start();
                    break;
                }
                case 3: {
                    studentProfileController.start();
                    break;
                }
                case 4: {
                    profileController.start();
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
        System.out.println("*** Admin Menu ***");
        System.out.println("1. Book");
        System.out.println("2. Category");
        System.out.println("3. Student");
        System.out.println("4. Profile");
        System.out.println("0. Exit");
    }
    @Autowired
    public AdminController(BookController bookController, CategoryController categoryController, StudentProfileController studentProfileController, ProfileController profileController, ScannerService scannerService) {
        this.bookController = bookController;
        this.categoryController = categoryController;
        this.studentProfileController = studentProfileController;
        this.profileController = profileController;
        this.scannerService = scannerService;
    }
}
