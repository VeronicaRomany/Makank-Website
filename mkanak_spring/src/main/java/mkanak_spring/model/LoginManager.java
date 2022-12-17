package mkanak_spring.model;

import mkanak_spring.model.entities.UserCredentials;

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


