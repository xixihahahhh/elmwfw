package elm.controller;

import elm.pojo.BusinessType;
import elm.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessTypeController {
    
    @Autowired
    private BusinessTypeService businessTypeService;
    
    @GetMapping("/business/types")
    public List<BusinessType> getAllTypes() {
        return businessTypeService.getAllTypes();
    }
    
    @GetMapping("/business/type/{typeid}")
    public BusinessType getTypeById(@PathVariable Integer typeid) {
        return businessTypeService.getTypeById(typeid);
    }
} 