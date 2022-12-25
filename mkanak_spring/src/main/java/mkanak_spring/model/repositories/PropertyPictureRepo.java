package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.PropertyPicture;
import mkanak_spring.model.entities.PropertyPictureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyPictureRepo extends JpaRepository<PropertyPicture, PropertyPictureId> {
}
