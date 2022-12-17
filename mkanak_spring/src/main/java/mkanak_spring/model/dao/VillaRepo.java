package mkanak_spring.model.dao;

import mkanak_spring.model.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillaRepo extends JpaRepository<Villa, Long> {
}
