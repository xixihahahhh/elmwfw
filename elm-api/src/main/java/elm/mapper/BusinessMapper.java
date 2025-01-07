package elm.mapper;

import elm.pojo.Business;
import elm.pojo.Food;
import elm.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusinessMapper {

    @Select("select * from business")
    @Results({
        @Result(id = true, column = "businessid", property = "businessid"),
        @Result(column = "typeid", property = "businessType", 
            one = @One(select = "com.gw.elmprojecths.mapper.BusinessTypeMapper.getTypeById"))
    })
    List<Business> selectAll();

    //检索首页商家信息
    @Select("select * from business limit 0,5")
    @Results({
        @Result(id = true, column = "businessid", property = "businessid"),
        @Result(column = "typeid", property = "businessType", 
            one = @One(select = "com.gw.elmprojecths.mapper.BusinessTypeMapper.getTypeById"))
    })
    public List<Business> getIndexBusiness();

    //根据点餐分类检索商家信息
    @Select("select * from business where ordertypeid=#{typeid}")
    @Results({
        @Result(id = true, column = "businessid", property = "businessid"),
        @Result(column = "typeid", property = "businessType", 
            one = @One(select = "com.gw.elmprojecths.mapper.BusinessTypeMapper.getTypeById"))
    })
    public List<Business> getBusinessByType(int typeid);

    //登录
    @Select("select * from user where userid=#{userid} and password=#{password}")
    public User checkLogin(User user);

    //根据商家id检索商家信息
    @Select("select * from business where businessid=#{bid}")
    //映射关系，一对多，一个商家->多个商品
    @Results({
        @Result(id = true, column = "businessid", property = "businessid"),
        @Result(column = "businessid", property = "foodList", many = @Many(select = "getFoodList")),
        @Result(column = "typeid", property = "businessType", 
            one = @One(select = "com.gw.elmprojecths.mapper.BusinessTypeMapper.getTypeById"))
    })
    public Business getBusinessById(int bid);

    //根据商家id检索商品信息
    @Select("select foodid, foodname, foodexplain, foodimg, foodprice, businessid, remarks, categoryid from food where businessid=#{bid}")
    public List<Food> getFoodList(int bid);
}