package mkanak_spring.model;
import mkanak_spring.model.entities.PhoneNumber;
import mkanak_spring.model.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class SignupManager {
    public User signUpUser(JSONObject userObject) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(userObject.toString());

        Long id = (Long) object.get("user_id");
        User userInstance = new User();
        userInstance.setUserID(id);
        userInstance.setAddress((String) object.get("address"));
        userInstance.setUsername((String) object.get("username"));
        userInstance.setName((String) object.get("name"));
        userInstance.setDescription((String) object.get("description"));
        userInstance.setPassword((String) object.get("password"));
        userInstance.setEmail((String) object.get("email"));
        userInstance.setProfilePicLink((String) object.get("profile_pic_link"));
        return userInstance;
    }
    public List<PhoneNumber> addPhoneNumbers(User userInstance, JSONObject user) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(user.toString());
        JSONArray phoneNos = (JSONArray) object.get("phone_numbers");
        List<PhoneNumber> numbers = new ArrayList<>();
        for(Object obj : phoneNos){
            String message = (String) obj;
            PhoneNumber phoneNumber = new PhoneNumber(userInstance.getUserID(), message);
            numbers.add(phoneNumber);
        }
        return numbers;
    }
}
