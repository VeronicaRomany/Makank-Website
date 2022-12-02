package mkanak_spring.models;

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
    private Long user_id;
    @Column(name = "phone_number")
    private String phone_number;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
