package mkanak_spring;

public class UserCredentials {
    String userName;
    String password;
    int userID;
    UserCredentials(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public UserCredentials getUserData(){
       DomeDatabase domeDatabase=new DomeDatabase();
       DomeDatabase.Data d=domeDatabase.getData(this.userName,this.password);
       this.userID=d.userID;
       return this;
    }

}
