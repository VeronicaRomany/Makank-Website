package mkanak_spring.models.services;

import mkanak_spring.models.PhoneNumber;
import mkanak_spring.models.User;
import mkanak_spring.models.dao.PhoneNumberRepo;
import mkanak_spring.models.dao.UserRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PhoneNumberDAO phoneNumberRepo;
    @Override
    public String saveUser(JSONObject user) throws ParseException {
        JSONParser parser = new JSONParser(); // = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(user.toString());
        JSONArray phoneNos = (JSONArray) object.get("phone_numbers");
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


    /**
     * call DAO class

}
*/
