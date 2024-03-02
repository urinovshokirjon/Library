package dasturlash.uz.sevice;

import dasturlash.uz.Util.MD5Util;
import dasturlash.uz.Util.ProfileValidationUtil;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public void addProfile(Profile profile){
        if (!ProfileValidationUtil.isValid(profile)) {
            return;
        }

        Profile existProfile = profileRepository.getByLoginSpring(profile.getLogin());

        if (existProfile != null) {
            System.out.println("Bunday loginli foydalanuvchi mavjud boshqa login kiriting!!! ");
            return;
        }

        profile.setCreatedDate(LocalDateTime.now());
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setPassword(MD5Util.encode(profile.getPassword().trim()));
        int effectedRow = profileRepository.createSpring(profile);
        if (effectedRow == 1) {
            System.out.println("Siz muvaffaqiyatli Profileni qo'shdingiz");
        }

    }

    public void list(){
        List<Profile> profileList =profileRepository.getAllSpring(ProfileRoles.ADMIN,ProfileRoles.STAFF);
        profileList.forEach(profile -> {
            System.out.println(profile.getFild());
        });
    }
    public void studentlist(){
        List<Profile> profileList = profileRepository.getAllSpring(ProfileRoles.STUDENT);
        profileList.forEach(profile -> {
            System.out.println(profile.getFild());
        });
    }

    public void search(String query) {
        Profile profile = null;
        if (ProfileValidationUtil.isOnlyNumber(query)) {
            profile = profileRepository.getByIdSpring(Integer.parseInt(query));
        }
        List<Profile> profileList = profileRepository.searchProfileSpring(query);

        if (profile != null) {
            profileList.add(profile);
        }
        profileList.forEach(p -> {
            System.out.println(p.getFild());

        });
    }

    public void chargeStatus(int id) {
        Profile profile = profileRepository.getByIdSpring(id);
        if (profile==null){
            System.out.println("Bunday id yo'q");
        return;
        }
        int effectorRow;
        if (profile.getStatus().equals(ProfileStatus.ACTIVE)){
            // BLOCK
          effectorRow= profileRepository.updateStatusSpring(id,ProfileStatus.BLOCK);
        }else {
            // ACTIVE
            effectorRow= profileRepository.updateStatusSpring(id,ProfileStatus.ACTIVE);
        }

        if (effectorRow==1){
            System.out.println(profile.getName()+" Statusi o'zgardi ");
        }
        else {
            System.out.println(profile.getName()+" Statusi o'zgarmadi ");
        }

    }

    public void searchStudent(String query) {
        Profile profile = null;
        if (ProfileValidationUtil.isOnlyNumber(query)) {
            profile = profileRepository.getByIdSpring(Integer.parseInt(query));
        }
        List<Profile> profileList = profileRepository.searchStudentSpring(query);

        if (profile != null) {
            profileList.add(profile);
        }
        profileList.forEach(p -> {
            System.out.println(p.getFild());

        });
    }

    public void chargeStatusStudent(int id,ProfileStatus status) {
        Profile profile = profileRepository.getByIdSpring(id);
        if (profile==null){
            System.out.println("Bunday id yo'q");
            return;
        }

        if (!profile.getRoles().equals(ProfileRoles.STUDENT)){
            System.out.println("Siz bu ishni qilolmaysiz");
            return;
        }
        int effectorRow= profileRepository.updateStatusSpring(id,status);
            // BLOCK
            if (effectorRow==1){
                System.out.println(profile.getName()+" Statusi o'zgardi");
            }
    }
    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
}
