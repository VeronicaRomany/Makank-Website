package mkanak_spring.model.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserService {
    String saveUser(JSONObject user) throws ParseException;
    Long logInUser(String username, String password);
    // edit user
    // get user
}
