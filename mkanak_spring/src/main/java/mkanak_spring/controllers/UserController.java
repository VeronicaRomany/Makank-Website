package mkanak_spring.controllers;

import com.google.gson.Gson;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityGuard securityGuard;

    @PostMapping(value = "/signin")
    public JSONObject signInUser(@RequestBody JSONObject jsonObject) {
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        Long responseID = userService.logInUser(username, password);
        if(responseID==-1L || responseID == -2L){
            JSONObject response = new JSONObject();
            response.put("id",responseID);
            response.put("token",null);
            return response;
        } else {
            String token = securityGuard.getJWT(responseID);
            JSONObject response = new JSONObject();
            response.put("id",responseID);
            response.put("token",token);
            return response;
        }
    }

    @PostMapping(value= "/new")
    public JSONObject signUpUser(@RequestBody JSONObject user) throws ParseException {
        Long responseID = userService.createUser(user);
        if(responseID==-1L){
            JSONObject response = new JSONObject();
            response.put("id",responseID);
            response.put("token",null);
            return response;
        } else {
            String token = securityGuard.getJWT(responseID);
            JSONObject response = new JSONObject();
            response.put("id",responseID);
            response.put("token",token);
            return response;
        }
    }

    @GetMapping("/profile/{userID}")
    public User getUserInfo(@PathVariable long userID){
        Gson gson = new Gson();
        long u = gson.fromJson(String.valueOf(userID),long.class);
        return userService.findUserInfoByUseId(u);
    }


    @PostMapping(value = "/profile/edit/{userId}")
    public boolean editUser(@PathVariable int userId, @RequestHeader("Authorization") String bearerToken,
                            @RequestBody JSONObject jsonObject) throws Exception {

        int idJson = userId;
        if(!securityGuard.verifyJWTtoken(idJson,bearerToken))
            throw new Exception("error");
        return userService.editUser(jsonObject,userId);
    }

}
