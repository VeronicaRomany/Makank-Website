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
        return userRepo.findByUsername(username);
    }

    public Boolean emailExists(String email) {
        return userRepo.findByEmail(email);
    }

    public User findUser(long userID) {
        System.out.println("test:"+userID);
        return userRepo.findUserInfoByUseName(userID);}
}
