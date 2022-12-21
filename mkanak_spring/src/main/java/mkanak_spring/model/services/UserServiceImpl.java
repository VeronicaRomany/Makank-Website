package mkanak_spring.model.services;

import mkanak_spring.model.*;
import mkanak_spring.model.dao.PhoneNumberRepo;
import mkanak_spring.model.dao.UserDAO;
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
    PhoneNumberRepo phoneNumberRepo;


    @Override
    //@Transactional
    public Long saveUser(JSONObject user) throws ParseException {
        SignupManager signupManager = new SignupManager();
        User userInstance = signupManager.signUpUser(user);
        if(userDAO.usernameExists(userInstance.getUsername())) return -1L;

        userDAO.saveUser(userInstance);
        List<PhoneNumber> numbers = signupManager.addPhoneNumbers(userInstance, user);
        phoneNumberRepo.saveAll(numbers);
     //   userInstance.setPhoneNumbers(numbers);

        return userInstance.getUserID();
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
    @Override
    public User findUserInfoByUseId(long userID) {
        return userDAO.findUserInfoByUseId(userID);
    }
    @Override
    public String findUserPhoneByUseId(long userID) {
        return userDAO.findUserPhoneByUseId(userID);
    }
}