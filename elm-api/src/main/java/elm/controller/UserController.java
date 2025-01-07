package elm.controller;

import elm.pojo.User;
import elm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userService.findUser(user.getUserid(),user.getPassword());
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody User user){
        try{
            userService.updateInfo(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
}
