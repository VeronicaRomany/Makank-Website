package mkanak_spring.model.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import mkanak_spring.model.entities.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String name;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true)
    private String email;
    private String address;
    @Column(name = "profile_pic_link")
    private String profilePicLink;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_description")
    private String description;

   // @OneToMany()
  //  @JoinColumn(referencedColumnName = "user_id")
  //  private List<PhoneNumber> phoneNumbers = new ArrayList<>();


    //private List<String> savedItems = new ArrayList<>(); // to be updated


    public User(Long userID, String name, String username, String email, String address, String profilePicLink, String password, String description, List<PhoneNumber> phoneNumbers) {
        this.userID = userID;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.profilePicLink = profilePicLink;
        this.password = password;
        this.description = description;
       // this.phoneNumbers = phoneNumbers;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfilePicLink() {
        return profilePicLink;
    }

    public void setProfilePicLink(String profilePicLink) {
        this.profilePicLink = profilePicLink;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

// //   public List<PhoneNumber> getPhoneNumbers() {
//        return phoneNumbers;
//    }

//    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
//        this.phoneNumbers = phoneNumbers;
//    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + userID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", profile_pic_link='" + profilePicLink + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
           //     ", phone_numbers=" + phoneNumbers +
                '}';
    }
}
