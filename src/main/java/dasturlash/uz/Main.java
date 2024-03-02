package dasturlash.uz;
import dasturlash.uz.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context=new ClassPathXmlApplicationContext("spring-core.xml");
        MainController mainController = context.getBean("mainController", MainController.class);
        mainController.start();
        System.out.println(" Xayr ishlaringizga omad Allohni panohida bo'ling!");

    }


}