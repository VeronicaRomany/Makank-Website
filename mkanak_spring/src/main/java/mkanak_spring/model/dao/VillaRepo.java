package mkanak_spring.model.dao;

import mkanak_spring.model.entities.Villa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillaRepo extends JpaRepository<Villa, Long> {

}
