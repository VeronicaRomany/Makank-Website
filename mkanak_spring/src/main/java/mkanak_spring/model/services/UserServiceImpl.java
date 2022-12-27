package mkanak_spring.model.services;

import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import mkanak_spring.model.dao.UserDAO;
import mkanak_spring.model.repositories.UserCredentialsRepo;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserCredentialsRepo userCredentialsRepo;
    @Override
    //@Transactional
    public Long createUser(JSONObject user) throws ParseException {
        JsonToObject converter = new JsonToObject();
        User userInstance = converter.getUserFromJson(user);
        userInstance.setUserID(null);
        if(userDAO.usernameExists(userInstance.getUsername())) return -1L;
        userDAO.saveUser(userInstance);
        return userCredentialsRepo.findByUsername(userInstance.getUsername()).getUserID();
    }


    @Override
    public Long logInUser(String username, String password) {
        JsonToObject loginManager = new JsonToObject();
        UserCredentials userCredentials = loginManager.SignInUser(username, password);
        userCredentials = userDAO.logInUser(userCredentials);
        if(userCredentials == null) return -1L;
        if(!userCredentials.getPassword().equals(password)) return -2L;
        return userCredentials.getUserID();
    }
    @Override
    public User findUserInfoByUseId(long userID) {
        return userDAO.findUserInfoByUseId(userID);
    }
//    @Override
//    public String findUserPhoneByUseId(long userID) {
//        return userDAO.findUserPhoneByUseId(userID);
//    }

    @Override
    public boolean editUser(JSONObject userJson)  {
        // TODO validate user

        JsonToObject converter = new JsonToObject();
        User user = null;
        try {
            user = converter.getUserFromJson(userJson);
        } catch (ParseException e) {
            return false;
        }
        userDAO.saveUser(user);
        return true;
    }
}