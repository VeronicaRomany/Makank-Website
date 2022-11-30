package com.example.demo;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;

@CrossOrigin
@RestController
@RequestMapping("/makank")
public class userController {

    @PostMapping("/signin")
    public int signInUser(@RequestBody String data) throws JSONException {
        JSONObject json = new JSONObject(data);
        System.out.println(json.getString("username"));
        String userName=json.getString("username");
        String password=json.getString("password");
        LoginManager loginManager=new LoginManager();
       int userID = loginManager.SignInUser(userName,password);
        System.out.println(userID);

       return userID;

    }


}
