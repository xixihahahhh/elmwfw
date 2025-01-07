package elm.controller;

import elm.pojo.Deliveryaddress;
import elm.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public List<Deliveryaddress> getList(String userId){
        return addressService.findAll(userId);
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Deliveryaddress deliveryaddress){
        try{
            addressService.createdOne(deliveryaddress);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/del/{id}")
    public boolean del(@PathVariable("id") Integer id){
        try{
            addressService.deleteDeliveryaddress(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getInfo")
    public Deliveryaddress getInfo(Integer daid){
        return addressService.findByDaid(daid);
    }

    @PostMapping("/update")
    public boolean updateInfo(@RequestBody Deliveryaddress deliveryaddress){
        try{
            addressService.updateDeliveryaddress(deliveryaddress);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
