package mkanak_spring.model.repositories;

import jakarta.transaction.Transactional;
import mkanak_spring.model.entities.PropertyPicture;
import mkanak_spring.model.entities.PropertyPictureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyPictureRepo extends JpaRepository<PropertyPicture, PropertyPictureId> {
    @Transactional
    @Modifying
    @Query(value = "delete from property_pictures p where p.post_id = ?1", nativeQuery = true)
    void deletePicturesById(Long postID);
}
