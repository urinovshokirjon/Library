package dasturlash.uz.sevice;

import dasturlash.uz.container.ComponentContainer;
import dasturlash.uz.dto.Category;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

public void create(Category category){
    if (!check(category)){
        System.out.println("Category nomida hatolik bor!!!");
        return;
    }

    Category exist = categoryRepository.getByNameSpring(category.getName());
    if (exist !=null){
        System.out.println("Bunday Category oldindan mavjud!!!");
        return;
    }
    category.setCreateDate(LocalDateTime.now());
    category.setVisible(true);
    int effectedRow = categoryRepository.saveSpring(category);
    if (effectedRow !=0){
        System.out.println(category.getName()+" Categorya yaratildi!!!");
    }

}
public boolean  check(Category category){
    if (category.getName()==null || category.getName().isBlank() || category.getName().length() <=3){
        return false;
    }
    return true;

}

    public void list() {
        List<Category> categoryList = categoryRepository.getAllSpring();
        for (Category category: categoryList){
            System.out.println(category.getId()+" "+category.getName()+" "+category.getCreateDate());
        }
    }

    public void delete(int id) {
        int effectedRows = categoryRepository.deleteByIdSpring(id);
        if (effectedRows !=0){
            System.out.println("Category deleted");
        }else {
            System.out.println("Category not deleted");
        }
    }

        @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
