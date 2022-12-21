package mkanak_spring.model.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface UserService {
    Long saveUser(JSONObject user) throws ParseException;
    Long logInUser(String username, String password);
    User findUserInfoByUseName(long userID);
    // edit user
    // get user
}
