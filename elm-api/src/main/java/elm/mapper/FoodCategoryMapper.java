package elm.mapper;

import elm.pojo.FoodCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodCategoryMapper {
    
    @Select("select * from food_category")
    List<FoodCategory> getAllCategories();
    
    @Select("select * from food_category where categoryid = #{categoryid}")
    FoodCategory getCategoryById(Integer categoryid);
} 