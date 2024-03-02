package dasturlash.uz.controller;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.sevice.ProfileService;
import dasturlash.uz.sevice.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileController {

    private ProfileService profileService;
    private ScannerService scannerService;
    public void start() {
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Profile Menu ga ham o'tib oldik!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                    profileService.list();
                    break;
                }
                case 2: {
                    search();
                    break;
                }
                case 3: {
                    changeStatus();
                    break;
                }
                case 4: {
                    addProfile();
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
        System.out.println("*** Profile Menu ***");
        System.out.println("1. Profile list");
        System.out.println("2. Search profile");
        System.out.println("3. Change profile status");
        System.out.println("4. Profile add");
        System.out.println("0. Exit");
    }

    public void addProfile() {
        System.out.print("Enter name: ");
        String name = scannerService.getStrscanner().next();

        System.out.print("Enter surname: ");
        String surname = scannerService.getStrscanner().next();

        System.out.print("Enter login: ");
        String login = scannerService.getStrscanner().next();

        System.out.print("Enter password: ");
        String password = scannerService.getStrscanner().next();

        System.out.print("Enter phone: ");
        String phone = scannerService.getStrscanner().next();

        System.out.print("Enter role (STAFF,ADMIN): ");
        String role = scannerService.getStrscanner().next();

        ProfileRoles profileRoles;
        try {
            profileRoles = ProfileRoles.valueOf(role);
        } catch (RuntimeException e) {
            System.out.println("Roleni xato kiritding!!!");
            return;
        }

        Profile profile = new Profile();
        profile.setName(name.trim());
        profile.setSurname(surname.trim());
        profile.setLogin(login.trim());
        profile.setPassword(password);
        profile.setPhone(phone.trim());
        profile.setRoles(profileRoles);

        profileService.addProfile(profile);
    }

    public void search(){
        System.out.print("Enter query: ");
        String query = scannerService.getStrscanner().next();
        profileService.search(query);
    }


    public void changeStatus(){
        System.out.print("Enter id: ");
        int id = scannerService.getIntscanner().nextInt();
        profileService.chargeStatus(id);
    }
    @Autowired
    public ProfileController(ProfileService profileService, ScannerService scannerService) {
        this.profileService = profileService;
        this.scannerService = scannerService;
    }
}
