package mkanak_spring.controllers;

import com.google.gson.Gson;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.dao.UserDAO;
import mkanak_spring.model.services.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
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

//    @GetMapping("/profile/phone/{userIdPhone}")
//    public String getUserPhone(@PathVariable long userIdPhone){
//        Gson gson = new Gson();
//        System.out.println("REQUEST : "+userIdPhone);
//        long u= gson.fromJson(String.valueOf(userIdPhone),long.class);
//        String test = userService.findUserPhoneByUseId(u);
//        System.out.println(test);
//        return test;
//    }


    @PostMapping(value = "/profile/edit")
    public boolean editUser(@RequestHeader("Authentication") String bearerToken,@RequestBody JSONObject jsonObject) throws Exception {
        String jwt;
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            jwt = bearerToken.substring(7);
        }else throw new Exception("Token not properly defined");

        if(!securityGuard.verifyJWT(jwt))
            throw new Exception("Token defined, but invalid");

        Long id = securityGuard.extractIdFromJWT(jwt);
        if(id != jsonObject.get("user_id")){
            System.out.println("IDs doesn't match");
            throw new Exception("Token defined, valid, id doesn't match");
        }

        return userService.editUser(jsonObject);
    }



}