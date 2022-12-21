package mkanak_spring.model.services;

import mkanak_spring.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface UserService {
    Long saveUser(JSONObject user) throws ParseException;
    Long logInUser(String username, String password);
    List<String> getNumber(long user_id);
    // edit user
    // get user
}
