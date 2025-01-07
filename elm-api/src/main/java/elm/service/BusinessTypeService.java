package elm.service;

import elm.pojo.BusinessType;
import java.util.List;

public interface BusinessTypeService {
    List<BusinessType> getAllTypes();
    BusinessType getTypeById(Integer typeid);
} 