package mkanak_spring.controllers;

import mkanak_spring.models.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "users")
    public Long getUsers(@RequestBody JSONObject userCredentials) {
        String username = (String) userCredentials.get("username");
        String password = (String) userCredentials.get("password");
        return userService.logInUser(username, password);
    }

    @PostMapping(value= "/users/new")
    public String saveUser(@RequestBody JSONObject user) throws ParseException {
        return userService.saveUser(user);
    }
}
