package mkanak_spring.model.dao;

import mkanak_spring.model.User;
import mkanak_spring.model.UserCredentials;
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
    public UserCredentials logInUser(UserCredentials userCredentials) {
        userCredentials = userCredentialsRepo.findByUsername(userCredentials.getUsername());
        return userCredentials;
    }

    public Boolean usernameExists(String username) {
        if(userRepo.findByUsername(username)) return true;
        return false;
    }

    public Boolean emailExists(String email) {
        if(userRepo.findByEmail(email)) return true;
        return false;
    }

}
