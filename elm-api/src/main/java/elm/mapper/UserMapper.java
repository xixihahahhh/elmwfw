package elm.mapper;

import elm.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface UserMapper {

    @Select("select * from user where userId = #{userId} and password = #{password}")
    User selectUser(@Param("userId") String userId, @Param("password") String password);

    @Update("update user set password = #{password} ,userName = #{username},userSex = #{usersex} where userId = #{userid}")
    void updateUserInfo(User user);
    
    @Insert("insert into user (userId, password, username, usersex, deltag) values (#{userid}, #{password}, #{username}, #{usersex}, 1)")
    void insertUser(User user);
    
    @Select("select count(*) from user where userId = #{userId}")
    int checkUserExists(@Param("userId") String userId);

    @Select("select * from user where userid = #{userId}")
    User selectUserById(String userId);
}
