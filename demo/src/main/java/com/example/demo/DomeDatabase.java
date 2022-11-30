package com.example.demo;

import java.util.HashSet;
import java.util.Set;

public class DomeDatabase {
    class Data{
        String userName;
        String password;
        int userID;
        Data(String userName, String password, int userID){
            this.userName=userName;
            this.password=password;
            this.userID=userID;
        }
    }

    Set<Data> data=new HashSet<>();
    DomeDatabase(){
        data.add(new Data("A","p1",1));
        data.add(new Data("B","p2",2));
        data.add(new Data("C","p3",3));
    }
    public Data getData(String userName,String password){
        for (Data d : this.data){
            if(d.userName.equals(userName)&&d.password.equals(password)){
                return d;
            }

        }
        return new Data("-1","-1",-1);
    }

}
