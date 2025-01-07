package elm.mapper;

import elm.pojo.Deliveryaddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from deliveryaddress where userId = #{userId}")
    List<Deliveryaddress> selectAll(String userId);

    @Insert("insert into deliveryaddress values (null,#{contactname},#{contactsex},#{contacttel},#{address},#{userid})")
    void insertOne(Deliveryaddress deliveryaddress);

    @Delete("delete from deliveryaddress where daid = #{id}")
    void deleteOne(Integer id);

    @Select("select * from deliveryaddress where daId = #{daid}")
    Deliveryaddress selectByDaid(Integer daid);

    @Update("update deliveryaddress set contactName = #{contactname},contactSex = #{contactsex},contactTel = #{contacttel},address = #{address} where daId = #{daid}")
    void updateInfo(Deliveryaddress deliveryaddress);
}
