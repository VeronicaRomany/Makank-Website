package mkanak_spring.model.services;

import mkanak_spring.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface UserService {
    Long saveUser(JSONObject user) throws ParseException;
    Long logInUser(String username, String password);
    User findUserInfoByUseId(long userID);
    String findUserPhoneByUseId(long userID);
    // edit user
    // get user
}
