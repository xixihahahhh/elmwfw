package elm.controller;

import elm.pojo.FoodCategory;
import elm.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodCategoryController {
    
    @Autowired
    private FoodCategoryService foodCategoryService;
    
    @GetMapping("/food/categories")
    public List<FoodCategory> getAllCategories() {
        return foodCategoryService.getAllCategories();
    }
    
    @GetMapping("/food/category/{categoryid}")
    public FoodCategory getCategoryById(@PathVariable Integer categoryid) {
        return foodCategoryService.getCategoryById(categoryid);
    }
} 