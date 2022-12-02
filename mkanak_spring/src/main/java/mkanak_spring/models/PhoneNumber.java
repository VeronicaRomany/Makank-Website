package mkanak_spring.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "phone_numbers")
@NoArgsConstructor
@IdClass(PhoneNumberId.class)
public class PhoneNumber {
    @Id
    @Column(name = "user_id")
    private Long user_id;
    @Id
    @Column(name = "phone_number")
    private String phone_number;

    public PhoneNumber(Long user_id, String phone_number) {
        this.user_id = user_id;
        this.phone_number = phone_number;
    }

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


