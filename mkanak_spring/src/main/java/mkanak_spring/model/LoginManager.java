package mkanak_spring.model;

import mkanak_spring.model.UserCredentials;
import mkanak_spring.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginManager {
    UserCredentials userCredentials;
    public LoginManager(){}

    public UserCredentials SignInUser(String userName, String password){
        this.userCredentials=new UserCredentials(userName,password);
        return this.userCredentials;
    }
    public UserCredentials getUserCredentials(){
        return this.userCredentials;
    }

}


