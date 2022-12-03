package mkanak_spring.model.dao;

import mkanak_spring.model.PhoneNumberId;
import mkanak_spring.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, PhoneNumberId> {
}

