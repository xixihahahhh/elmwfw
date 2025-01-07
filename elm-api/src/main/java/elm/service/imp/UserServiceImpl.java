package elm.service.imp;

import elm.mapper.UserMapper;
import elm.pojo.User;
import elm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUser(String userId, String password) {
        return userMapper.selectUser(userId, password);
    }

    @Override
    public void updateInfo(User user) {
        userMapper.updateUserInfo(user);
    }
    
    @Override
    public boolean register(User user) {
        // 检查用户是否已存在
        if (userMapper.checkUserExists(user.getUserid()) > 0) {
            return false;
        }
        
        try {
            userMapper.insertUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectUserById(userId);
    }
}
