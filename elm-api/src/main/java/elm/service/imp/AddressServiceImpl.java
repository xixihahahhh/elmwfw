package elm.service.imp;

import elm.mapper.AddressMapper;
import elm.pojo.Deliveryaddress;
import elm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Deliveryaddress> findAll(String userId) {
        return addressMapper.selectAll(userId);
    }

    @Override
    public void createdOne(Deliveryaddress deliveryaddress) {
        addressMapper.insertOne(deliveryaddress);
    }

    @Override
    public void deleteDeliveryaddress(Integer id) {
        addressMapper.deleteOne(id);
    }

    @Override
    public Deliveryaddress findByDaid(Integer daid) {
        return addressMapper.selectByDaid(daid);
    }

    @Override
    public void updateDeliveryaddress(Deliveryaddress deliveryaddress) {
        addressMapper.updateInfo(deliveryaddress);
    }
}
