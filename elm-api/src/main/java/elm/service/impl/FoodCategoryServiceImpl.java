package elm.service.impl;

import elm.mapper.FoodCategoryMapper;
import elm.pojo.FoodCategory;
import elm.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {
    
    @Autowired
    private FoodCategoryMapper foodCategoryMapper;
    
    @Override
    public List<FoodCategory> getAllCategories() {
        return foodCategoryMapper.getAllCategories();
    }
    
    @Override
    public FoodCategory getCategoryById(Integer categoryid) {
        return foodCategoryMapper.getCategoryById(categoryid);
    }
} 