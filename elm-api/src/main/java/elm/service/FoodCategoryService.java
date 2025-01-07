package elm.service;

import elm.pojo.FoodCategory;
import java.util.List;

public interface FoodCategoryService {
    List<FoodCategory> getAllCategories();
    FoodCategory getCategoryById(Integer categoryid);
} 