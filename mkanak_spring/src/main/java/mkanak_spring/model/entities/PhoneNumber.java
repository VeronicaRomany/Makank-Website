package mkanak_spring.model.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phone_numbers")
@NoArgsConstructor
@IdClass(PhoneNumberId.class)
public class PhoneNumber {
    @Id
    @Column(name = "user_id")
    private Long userID;
    @Id
    @Column(name = "phone_number")
    private String phoneNumber;

    public PhoneNumber(Long userID, String phoneNumber) {
        this.userID= userID;
        this.phoneNumber = phoneNumber;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


