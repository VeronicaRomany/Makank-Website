package mkanak_spring.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberId implements Serializable {
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "phone_number")
    private String phoneNumber;

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
