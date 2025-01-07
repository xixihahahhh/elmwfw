package elm.service;

import elm.pojo.Business;
import elm.pojo.User;

import java.util.List;
public interface BusinessService {

    List<Business> findAll();

    //检索首页商家信息
    public List<Business> getIndexBusiness();
    //根据点餐分类检索商家信息
    public List<Business>getBusinessByType(int typeid);
    public User checkLogin(User user);
    //根据商家id检索商家信息
    public Business getBusinessById(int bid);


}
