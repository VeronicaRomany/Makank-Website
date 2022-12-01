package mkanak_spring.models.dao;

import mkanak_spring.models.PhoneNumberId;
import mkanak_spring.models.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, PhoneNumberId> {
}

