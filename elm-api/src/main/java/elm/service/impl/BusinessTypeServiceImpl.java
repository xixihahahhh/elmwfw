package elm.service.impl;

import elm.mapper.BusinessTypeMapper;
import elm.pojo.BusinessType;
import elm.service.BusinessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {
    
    @Autowired
    private BusinessTypeMapper businessTypeMapper;
    
    @Override
    public List<BusinessType> getAllTypes() {
        return businessTypeMapper.getAllTypes();
    }
    
    @Override
    public BusinessType getTypeById(Integer typeid) {
        return businessTypeMapper.getTypeById(typeid);
    }
} 