package dasturlash.uz.sevice;

import dasturlash.uz.Util.ProfileValidationUtil;
import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.Util.MD5Util;
import dasturlash.uz.controller.AdminController;
import dasturlash.uz.controller.StaffController;
import dasturlash.uz.controller.StudentController;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AuthService {
    private ProfileRepository profileRepository;
    private StudentController studentController;
    private AdminController adminController;
    private StaffController staffController;


    public void login(String login, String password) {

        Profile profile = profileRepository.getByLoginSpring(login);
        if (profile == null) {
            System.out.println("Login or password wring!!!");
            return;
        }
        String md5 = MD5Util.encode(password);
        if (!md5.equals(profile.getPassword())) {
            System.out.println("Login or password wring!!!");
            return;
        }

        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            System.out.println("Kechirasiz siz BLOCK holatdasiz!!!");
            return;
        }

        System.out.println("*** Welcome to library project ***");
        ComponentContainer.currentProfile=profile;
        if (profile.getRoles().equals(ProfileRoles.STUDENT)) {
            //StudentController;
            studentController.start();
        }
        else if(profile.getRoles().equals(ProfileRoles.ADMIN)) {
            //AminController;
            adminController.start();
        }
        else if(profile.getRoles().equals(ProfileRoles.STAFF)) {
            //StaffController;
            staffController.start();
        }

    }
    public void registration(Profile profile) {
        // Bu metod login va passwordni tekshirib agar MB bo'lmasa studentni registratsiya qiladi
        if (!ProfileValidationUtil.isValid(profile)) {
            return;
        }
        Profile existProfile = profileRepository.getByLoginSpring(profile.getLogin());

        if (existProfile != null) {
            System.out.println("Bunday loginli foydalanuvchi mavjud boshqa login kiriting!!! ");
            return;
        }
        profile.setPassword(MD5Util.encode(profile.getPassword().trim()));
        profile.setCreatedDate(LocalDateTime.now());
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setRoles(ProfileRoles.STUDENT);
        int effectedRow = profileRepository.createSpring(profile);
        if (effectedRow == 1) {
            System.out.println("Siz muvaffaqiyatli registratsiya bo'ldingiz");
        }

    }

   @Autowired
    public AuthService(ProfileRepository profileRepository, StudentController studentController, AdminController adminController, StaffController staffController) {
        this.profileRepository = profileRepository;
        this.studentController = studentController;
        this.adminController = adminController;
        this.staffController = staffController;
    }
}
