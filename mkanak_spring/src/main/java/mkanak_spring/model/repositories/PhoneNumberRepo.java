package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.PhoneNumberId;
import mkanak_spring.model.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, PhoneNumberId> {
}

