package mkanak_spring.models.dao;

import mkanak_spring.models.User;
import mkanak_spring.models.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    @Autowired
    UserCredentialsRepo userCredentialsRepo;
    @Autowired
    UserRepo userRepo;
    public void saveUser(User user) {
        userRepo.save(user);
    }
    public UserCredentials logInUser(String username) {
        UserCredentials userCredentials = userCredentialsRepo.findByUsername(username);
        return userCredentials;
    }

    public Boolean usernameExists(String username) {
        if(userRepo.findByUsername(username) != null) return true;
        return false;
    }

    public Boolean emailExists(String email) {
        if(userRepo.findByEmail(email) != null) return true;
        return false;
    }

}
