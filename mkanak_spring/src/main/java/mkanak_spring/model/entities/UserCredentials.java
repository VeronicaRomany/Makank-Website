package mkanak_spring.model.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@NoArgsConstructor
@Table(name = "login_credentials")
public class UserCredentials {
    @Id
    @Column(name = "user_id")
    private Long userID;
    private String username;
    @Column(name = "user_password")
    private String password;

    public UserCredentials(String userName, String password){
        this.username=userName;
        this.password=password;
    }


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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


