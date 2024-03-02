package dasturlash.uz.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;
@Service
public class ScannerService {
    private Scanner strscanner;
    private Scanner intscanner;
@Autowired
    public ScannerService(){
        strscanner=new Scanner(System.in);
        intscanner=new Scanner(System.in);
    }
    public Scanner getStrscanner() {
        return strscanner;
    }

    public Scanner getIntscanner() {
        return intscanner;
    }

    public  int getAction() {
        System.out.print("Entor Action: ");
        try {
            return intscanner.nextInt();
        } catch (InputMismatchException e) {
           intscanner.nextInt();
            System.out.println("\n Adashtingiz bunaqa komanda yo'q \n");
        }
        return -1;
    }

}
