//package mkanak_spring.model.dao;
//
//import mkanak_spring.model.entities.User;
//import mkanak_spring.model.entities.UserCredentials;
//import mkanak_spring.model.repositories.UserCredentialsRepo;
//import mkanak_spring.model.repositories.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDAO {
//    @Autowired
//    UserCredentialsRepo userCredentialsRepo;
//    @Autowired
//    UserRepo userRepo;
//    public void saveUser(User user) {
//        userRepo.save(user);
//    }
//    public UserCredentials logInUser(UserCredentials userCredentials) {
//        userCredentials = userCredentialsRepo.findByUsername(userCredentials.getUsername());
//        return userCredentials;
//    }
//
//    public Boolean usernameExists(String username) {
//        return userRepo.findByUsername(username);
//    }
//
//    public User findUserInfoByUseId(long userID) {
//        return userRepo.findUserInfoByUseId(userID);
//    }
//
//
//}
