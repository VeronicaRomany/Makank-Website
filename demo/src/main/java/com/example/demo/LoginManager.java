package com.example.demo;

public class LoginManager {
    UserCredentials userCredentials;
    LoginManager(){}


    public int SignInUser(String userName, String password){
        this.userCredentials=new UserCredentials(userName,password);
        return this.userCredentials.getUserData().userID;
    }
    public UserCredentials getUserCredentials(){
        return this.userCredentials;
    }

}
