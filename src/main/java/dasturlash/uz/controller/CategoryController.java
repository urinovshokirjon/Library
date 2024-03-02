package dasturlash.uz.controller;

import dasturlash.uz.dto.Category;
import dasturlash.uz.sevice.CategoryService;
import dasturlash.uz.sevice.ScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    private ScannerService scannerService;

    public void start() {
        boolean b = true;
        while (b) {
            System.out.println("============================================");
            System.out.println("Xudoga shukur Categoriya Menu ga ham o'tib oldik!");
            showMenu();
            int action = scannerService.getAction();
            switch (action) {
                case 1: {
                   categoryService.list();
                    break;
                }
                case 2: {
                    deleteCategory();
                    break;
                }
                case 3: {
                    addCategory();
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
        System.out.println("*** Category Menu ***");
        System.out.println("1. Category list");
        System.out.println("2. Delete category");
        System.out.println("3. Add category");
        System.out.println("0. Exit");
    }
    public void addCategory(){
        System.out.print("Enter name: ");
        String name=scannerService.getStrscanner().next();
        Category category=new Category();
        category.setName(name);
        categoryService.create(category);
    }

    public void deleteCategory(){
        System.out.print("Entor id: ");
        int id = scannerService.getIntscanner().nextInt();
       categoryService.delete(id);
    }
    @Autowired
    public CategoryController(CategoryService categoryService, ScannerService scannerService) {
        this.categoryService = categoryService;
        this.scannerService = scannerService;
    }
}
