package elm.service.imp;

import elm.mapper.BusinessMapper;
import elm.pojo.Business;
import elm.pojo.User;
import elm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessSeviceImp  implements BusinessService {
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public List<Business> findAll() {
        return businessMapper.selectAll();
    }


    @Override
    public List<Business> getIndexBusiness() {
        return businessMapper.getIndexBusiness();
    }

    @Override
    public List<Business> getBusinessByType(int typeid) {
        return businessMapper.getBusinessByType(typeid);
    }

    @Override
    public User checkLogin(User user) {
        return businessMapper.checkLogin(user);
    }

    @Override
    public Business getBusinessById(int bid) {
        return businessMapper.getBusinessById(bid);
    }


}
