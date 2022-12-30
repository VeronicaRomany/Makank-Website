package mkanak_spring.model;
import mkanak_spring.model.entities.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SignupManager {
    public User signUpUser(JSONObject userObject) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(userObject.toString());

      //  Long id = (Long) object.get("user_id");
        User userInstance = new User();
        userInstance.setUserID(null);
        userInstance.setAddress((String) object.get("address"));
        userInstance.setUsername((String) object.get("username"));
        userInstance.setName((String) object.get("name"));
      //  userInstance.setPhoneNumber((String) object.get("phone_number"));
        userInstance.setDescription((String) object.get("description"));
        userInstance.setPassword((String) object.get("password"));
        userInstance.setEmail((String) object.get("email"));
        userInstance.setProfilePicLink((String) object.get("profile_pic_link"));
        JSONArray phoneNos = (JSONArray) object.get("phone_numbers");
        for(Object obj : phoneNos){
            userInstance.setPhoneNumber((String) obj);
        }

        return userInstance;
    }

}
