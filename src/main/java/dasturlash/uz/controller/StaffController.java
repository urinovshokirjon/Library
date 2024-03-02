package dasturlash.uz.controller;

import dasturlash.uz.sevice.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StaffController {
    private BookController bookController;
    private StudentProfileController studentProfileController;
    private ScannerService scannerService;

    public void start() {
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Staff Menu ga ham o'tib oldik!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                   bookController.start();
                    break;
                }
                case 2: {
                   studentProfileController.start();
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
        System.out.println("*** Staff Menu ***");
        System.out.println("1. Book");
        System.out.println("2. Student");
        System.out.println("0. Exit");
    }

 @Autowired
    public StaffController(BookController bookController, StudentProfileController studentProfileController, ScannerService scannerService) {
        this.bookController = bookController;
        this.studentProfileController = studentProfileController;
        this.scannerService = scannerService;
    }
}
