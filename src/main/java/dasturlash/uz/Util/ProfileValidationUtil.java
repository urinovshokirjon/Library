package dasturlash.uz.Util;

import dasturlash.uz.dto.Profile;

public class ProfileValidationUtil {

    public static boolean isValid(Profile profile) {
        // Bu metod profile(student) ni hamma parametrini tekshiradi(name, surname, phone)
        if (profile.getName() == null || profile.getName().isBlank() || profile.getName().trim().length() < 2) {
            // trim() yozuvni oldi orqasidagi probil(bosh) joylarni qisqatirado,isEmpty() string ichi boshmi yoki to'lami teshiradi,isBlank() ham trim() ham isEmpty() ni ishini qiladi;
            System.out.println("Name is wrong");
            return false;
        }
        if (profile.getSurname() == null || profile.getSurname().isBlank() || profile.getSurname().length() < 2) {
            System.out.println("Surname is wrong");
            return false;
        }
        if (profile.getPassword() == null || profile.getPassword().isBlank() || profile.getPassword().length() < 5) {
            System.out.println("Password is wrong");
            return false;
        }
        if (profile.getLogin() == null || profile.getLogin().isBlank() || profile.getLogin().length() < 3) {
            System.out.println("Login is wrong");
            return false;
        }
        if (profile.getPhone() == null
                || profile.getPhone().isBlank()
                || profile.getPhone().length() != 12
                || !profile.getPhone().startsWith("998")
                || !isOnlyNumber(profile.getPhone())) {
            System.out.println("Phone is wrong");
            return false;
        }
        return true;
    }

    public static boolean isOnlyNumber(String input) {
        char[] arr = input.toCharArray();
        for (char c : arr) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

