package elm.service;

import elm.pojo.Deliveryaddress;

import java.util.List;

public interface AddressService {
    List<Deliveryaddress> findAll(String userId);

    void createdOne(Deliveryaddress deliveryaddress);

    void deleteDeliveryaddress(Integer id);

    Deliveryaddress findByDaid(Integer daid);

    void updateDeliveryaddress(Deliveryaddress deliveryaddress);
}
