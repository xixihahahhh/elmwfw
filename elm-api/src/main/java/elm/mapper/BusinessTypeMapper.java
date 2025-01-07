package elm.mapper;

import elm.pojo.BusinessType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessTypeMapper {
    
    @Select("select * from business_type")
    List<BusinessType> getAllTypes();
    
    @Select("select * from business_type where typeid = #{typeid}")
    BusinessType getTypeById(Integer typeid);
} 