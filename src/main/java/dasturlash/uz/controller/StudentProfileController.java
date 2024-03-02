package dasturlash.uz.controller;

import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.sevice.ProfileService;
import dasturlash.uz.sevice.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentProfileController {
    private ProfileService profileService;
    private ScannerService scannerService;

    public void start() {

        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur StudentProfile Menu ga ham o'tib oldik!");
            showMenu();
            int action =scannerService.getAction();
            switch (action) {
                case 1: {
                    profileService.studentlist();
                    break;
                }
                case 2: {
                    search();
                    break;
                }
                case 3: {
                    blockStudent();
                    break;
                }
                case 4: {
                    activeStudent();
                    break;
                }
                case 5: {
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
        System.out.println("*** StudentProfile Menu ***");
        System.out.println("1. Student list");
        System.out.println("2. Search student");
        System.out.println("3. Block student");
        System.out.println("4. Activate student");
        System.out.println("5. Student by book");
        System.out.println("0. Exit");
    }
    public void search(){
        System.out.print("Enter query: ");
        String query = scannerService.getStrscanner().next();
        profileService.searchStudent(query);
    }
    public void blockStudent(){
        System.out.print("Enter id: ");
       int id= scannerService.getIntscanner().nextInt();
        profileService.chargeStatusStudent(id, ProfileStatus.BLOCK);
    }
    public void activeStudent(){
        System.out.print("Enter id: ");
       int id= scannerService.getIntscanner().nextInt();
        profileService.chargeStatusStudent(id,ProfileStatus.ACTIVE);
    }
    @Autowired
    public StudentProfileController(ProfileService profileService, ScannerService scannerService) {
        this.profileService = profileService;
        this.scannerService = scannerService;
    }
}
