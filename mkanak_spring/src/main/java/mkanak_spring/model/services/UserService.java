package mkanak_spring.model.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import mkanak_spring.model.entities.User;
import java.util.List;

public interface UserService {
    Long createUser(JSONObject user) throws ParseException;
    Long logInUser(String username, String password);
    User findUserInfoByUseId(long userID);
//    String findUserPhoneByUseId(long userID);
    boolean editUser(JSONObject user,int userId) ;
}
