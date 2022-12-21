package mkanak_spring.controllers;

import com.google.gson.Gson;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.dao.UserDAO;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping()
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;

    @PostMapping(value = "users/signin")
    public Long signInUser(@RequestBody JSONObject userCredentials) {
        String username = (String) userCredentials.get("username");
        String password = (String) userCredentials.get("password");
        return userService.logInUser(username, password);
    }

    @PostMapping(value= "/users/new")
    public Long signUpUser(@RequestBody JSONObject user) throws ParseException {
        return userService.saveUser(user);
    }

    @GetMapping("/user/profile/{userID}")
    public User getUserInfo(@PathVariable long userID){
        Gson gson = new Gson();
        System.out.println("REQUEST : "+userID);
        long u= gson.fromJson(String.valueOf(userID),long.class);
        return userService.findUserInfoByUseId(u);
    }

    @GetMapping("/user/profile/phone/{userIdPhone}")
    public String getUserPhone(@PathVariable long userIdPhone){
        Gson gson = new Gson();
        System.out.println("REQUEST : "+userIdPhone);
        long u= gson.fromJson(String.valueOf(userIdPhone),long.class);
        String test = userService.findUserPhoneByUseId(u);
        System.out.println(test);
        return test;
    }

}
