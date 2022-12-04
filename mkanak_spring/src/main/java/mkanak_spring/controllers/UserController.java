package mkanak_spring.controllers;

import mkanak_spring.LoginManager;
import mkanak_spring.model.User;
import mkanak_spring.model.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping()
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping(value = "users/signin")
    public Long signInUser(@RequestBody JSONObject userCredentials) {
        /**
            JSONObject json = new JSONObject(data);
            System.out.println(json.getString("username"));
            String userName=json.getString("username");
            String password=json.getString("password");
            LoginManager loginManager=new LoginManager();
            int userID = loginManager.SignInUser(userName,password);
            System.out.println(userID);

            return userID;

        */
      //LoginManager loginManager = new LoginManager();
        String username = (String) userCredentials.get("username");
        String password = (String) userCredentials.get("password");
        return userService.logInUser(username, password);
    }

    @PostMapping(value= "/users/new")
    public String signUpUser(@RequestBody JSONObject user) throws ParseException {
        return userService.saveUser(user);
    }
}
