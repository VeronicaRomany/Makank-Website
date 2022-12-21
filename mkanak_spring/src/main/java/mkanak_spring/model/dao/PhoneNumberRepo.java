package mkanak_spring.model.dao;

import mkanak_spring.model.entities.PhoneNumberId;
import mkanak_spring.model.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, PhoneNumberId> {
    @Query("SELECT phoneNumber FROM PhoneNumber p where p.userID = ?1")
    List<String> getPhoneNumbers(long user_id);
}

