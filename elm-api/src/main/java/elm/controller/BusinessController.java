package elm.controller;

import elm.pojo.Business;
import elm.pojo.User;
import elm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//返回json字符串
@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @RequestMapping("getBusinessList")
    public List<Business> getBusinessList(){
        return businessService.findAll();
    }

    @RequestMapping("getIndexBusiness")
    public List<Business> getIndexBusiness(){
        return businessService.getIndexBusiness();
    }
    @RequestMapping("getBusinessByType")
    public List<Business> getBusinessByType(int typeid){
        return businessService.getBusinessByType(typeid);
    }
    @RequestMapping("checkLogin")
    public User checkLogin(User user){
        return businessService.checkLogin(user);
    }
    @RequestMapping("test")
    public User test(@RequestBody User user){
        System.out.println(user);
        return user;
    }
    @RequestMapping("getBusinessById/{bid}")
    public Business getBusinessById(@PathVariable int bid){
        return businessService.getBusinessById(bid);
    }
}
