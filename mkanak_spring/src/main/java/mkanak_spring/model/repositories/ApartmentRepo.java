package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
    @Query(value = "select post_id from apartment where for_students= :studentbool ;",nativeQuery = true)
    List<Long> getStudentHousingIDs(@Param("studentbool") boolean userID);
}
