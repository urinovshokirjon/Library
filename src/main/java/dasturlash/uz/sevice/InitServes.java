package dasturlash.uz.sevice;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.Util.MD5Util;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Bu class Deffolt Adminni bor yoki yo'qligini bo'lmasa yaratadi
@Service
public class InitServes {
    private ProfileRepository profileRepository;

    public void initAmin() {
// Bu metod Deffolt Adminni bor yoki yo'qligini aniqlaydi bo'lmasa yaratadi
        //login:admin, password=12345;
        String login="admin";
        Profile profile = profileRepository.getByLoginSpring("admin");
        if(profile==null){
        Profile admin=new Profile();
        admin.setName("Amin");
        admin.setSurname("Adminov");
        admin.setLogin(login);
        admin.setPassword(MD5Util.encode("12345"));
        admin.setPhone("998901234567");
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRoles(ProfileRoles.ADMIN);
        admin.setCreatedDate(LocalDateTime.now());
        profileRepository.createSpring(admin);

        }

    }
    public void initTestStudent() {
        // Bu metod bizga test Studentni  yaratadi shunchaki qilingan ishlarni tekshirish uchun
        //login:student, password=12345;
        String login="student";
        Profile profile = profileRepository.getByLoginSpring("student");
        if(profile==null){
            Profile student=new Profile();
            student.setName("Student");
            student.setSurname("Studentov");
            student.setLogin(login);
            student.setPassword(MD5Util.encode("12345"));
            student.setPhone("998901234567");
            student.setStatus(ProfileStatus.ACTIVE);
            student.setRoles(ProfileRoles.STUDENT);
            student.setCreatedDate(LocalDateTime.now());
            profileRepository.createSpring(student);

        }

    }

    @Autowired
    public InitServes(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
