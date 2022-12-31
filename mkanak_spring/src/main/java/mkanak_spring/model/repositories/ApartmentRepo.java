package mkanak_spring.model.repositories;

import jakarta.transaction.Transactional;
import mkanak_spring.model.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
    @Query(value = "select post_id from apartment where for_students= :studentbool ;",nativeQuery = true)
    List<Long> getStudentHousingIDs(@Param("studentbool") boolean userID);
    @Transactional
    @Modifying
    @Query(value = "delete from apartment a where a.post_id = ?1", nativeQuery = true)
    void deleteApartment(Long postID);
    @Transactional
    @Modifying
    @Query(value = "insert into apartment (post_id, has_elevator, apartment_level, for_students) " +
            "select :id,:elevator,:level,:for_students", nativeQuery = true)
    void insertApartment(@Param("id") Long postID, @Param("elevator") boolean hasElevator,
                         @Param("level") int level, @Param("for_students") boolean studentHousing);
}
