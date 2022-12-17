package mkanak_spring.model.services;

import mkanak_spring.model.LoginManager;
import mkanak_spring.model.entities.PhoneNumber;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import mkanak_spring.model.dao.PhoneNumberRepo;
import mkanak_spring.model.dao.UserDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    PhoneNumberRepo phoneNumberRepo;
    @Override
    //@Transactional
    public String saveUser(JSONObject user) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(user.toString());
        JSONArray phoneNos = (JSONArray) object.get("phone_numbers");
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

        if(userDAO.usernameExists(userInstance.getUsername())) return "Username already exists";
        if(userDAO.emailExists(userInstance.getEmail())) return "Account with same email exists";
        userDAO.saveUser(userInstance);
        List<PhoneNumber> numbers = new ArrayList<>();
        for(Object obj : phoneNos){
            String message = (String) obj;
            PhoneNumber phoneNumber = new PhoneNumber(userInstance.getUserID(), message);
            numbers.add(phoneNumber);
        }
        phoneNumberRepo.saveAll(numbers);
        userInstance.setPhoneNumbers(numbers);

        return "Saved + " + userInstance.getUserID();
    }

    @Override
    public Long logInUser(String username, String password) {
        LoginManager loginManager = new LoginManager();
        UserCredentials userCredentials = loginManager.SignInUser(username, password);
        userCredentials = userDAO.logInUser(userCredentials);
        if(userCredentials == null) return -1L;
        if(!userCredentials.getPassword().equals(password)) return -2L;
        return userCredentials.getUserID();
    }
}