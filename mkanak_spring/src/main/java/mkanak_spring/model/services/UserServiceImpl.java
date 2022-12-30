package mkanak_spring.model.services;

import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import mkanak_spring.model.repositories.UserCredentialsRepo;
import mkanak_spring.model.repositories.UserRepo;
import mkanak_spring.model.LoginManager;
import mkanak_spring.model.SignupManager;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserCredentialsRepo userCredentialsRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    //@Transactional
    public Long createUser(JSONObject user) throws ParseException {
        JsonToObject converter = new JsonToObject();
        User userInstance = converter.getUserFromJson(user);
        userInstance.setUserID(null);
        if(this.usernameExists(userInstance.getUsername())) return -1L;
        this.saveUser(userInstance);
        return userCredentialsRepo.findByUsername(userInstance.getUsername()).getUserID();
    }


    @Override
    public Long logInUser(String username, String password) {
        JsonToObject loginManager = new JsonToObject();
        UserCredentials userCredentials = loginManager.SignInUser(username, password);
        userCredentials = this.logInUser(userCredentials);
        if(userCredentials == null) return -1L;
        if(!userCredentials.getPassword().equals(password)) return -2L;
        return userCredentials.getUserID();
    }
    @Override
    public User findUserInfoByUseId(long userID) {
        return userRepo.findUserInfoByUseId(userID);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }
    public UserCredentials logInUser(UserCredentials userCredentials) {
        userCredentials = userCredentialsRepo.findByUsername(userCredentials.getUsername());
        return userCredentials;
    }

    public Boolean usernameExists(String username) {
        return userRepo.findByUsername(username);
    }

    public Boolean emailExists(String email) {
        return userRepo.findByEmail(email);
    }



    @Override
    public boolean editUser(JSONObject userJson,int userId)  {
        // TODO validate user
        JsonToObject converter = new JsonToObject();
        User user = null;
        try {
            user = converter.getUserFromJson(userJson,userId);
        } catch (ParseException e) {
            return false;
        }
        this.saveUser(user);
        return true;
    }
}

