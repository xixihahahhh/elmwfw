package elm.service;

import elm.pojo.User;

public interface UserService {
    User findUser(String userId,String password);

    void updateInfo(User user);
    
    boolean register(User user);

    User getUserById(String userId);
}
