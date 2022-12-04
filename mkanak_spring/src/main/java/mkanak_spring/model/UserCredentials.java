package mkanak_spring.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import mkanak_spring.DomeDatabase;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@NoArgsConstructor
@Table(name = "login_credentials")
public class UserCredentials {
    @Id
    private Long user_id;
    private String username;
    @Column(name = "user_password")
    private String password;

    public UserCredentials(String userName, String password){
        this.username=userName;
        this.password=password;
    }
    public UserCredentials getUserData(){
        DomeDatabase domeDatabase=new DomeDatabase();
        DomeDatabase.Data d=domeDatabase.getData(this.username,this.password);
      //  this.user_id=d.userID;
        return this;
    }
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


