package mkanak_spring.model.dao;

import mkanak_spring.model.PropertyPicture;
import mkanak_spring.model.PropertyPictureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyPictureRepo extends JpaRepository<PropertyPicture, PropertyPictureId> {
}
