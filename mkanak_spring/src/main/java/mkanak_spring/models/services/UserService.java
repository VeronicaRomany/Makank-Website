package mkanak_spring.models.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserService {
    String saveUser(JSONObject user) throws ParseException;

}
