package mkanak_spring.controllers;

import mkanak_spring.models.PhoneNumber;
import mkanak_spring.models.dao.PhoneNumberRepo;
import mkanak_spring.models.User;
import mkanak_spring.models.dao.UserRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
public class Controller {
    /**
     * Each method should call a service class
     */
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PhoneNumberRepo phoneNumberRepo;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value= "/users/new")
    public String saveUser(@RequestBody JSONObject user) throws ParseException {

        JSONParser parser = new JSONParser(); // = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(user.toString());
        JSONArray phoneNos = (JSONArray) object.get("phone_numbers");
        object.remove("phone_numbers");
     //   User objUser = (User) parser.parse(object.toString());

        Long id = (Long) object.get("user_id");
        User userInstance = new User();

        userInstance.setUserID(id);
        userInstance.setAddress((String) object.get("address"));
        userInstance.setUsername((String) object.get("username"));
        userInstance.setName((String) object.get("name"));
        userInstance.setDescription("");
        userInstance.setPassword("aaa");
        userInstance.setEmail((String) object.get("email"));
        userInstance.setProfile_pic_link("");


        userRepo.save(userInstance);
        List<PhoneNumber> numbers = new ArrayList<>();
        for(Object obj : phoneNos){
            String message = (String) obj;
            PhoneNumber phoneNumber = new PhoneNumber(userInstance.getUserID(), message);
            numbers.add(phoneNumber);
        }
        phoneNumberRepo.saveAll(numbers); // batch request
        userInstance.setPhoneNumbers(numbers);

        return "Saved + " + userInstance.getUserID();
    }

}
