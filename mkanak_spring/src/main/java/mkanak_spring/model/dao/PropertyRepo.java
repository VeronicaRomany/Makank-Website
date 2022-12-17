package mkanak_spring.model.dao;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {

    //    property_id,seller_id,property_type,price,city,property_address,area,info,rooms,bathrooms,for_rent,has_pictures
    @Query
            (value = "Select " +
                    "property_id,seller_id,property_type,price,city," +
                    "property_address,area,info,rooms,bathrooms," +
                    "for_rent,has_pictures " +
                    "from saved_items natural join properties where user_id = :id ;", nativeQuery = true)
    List<Property> findAllSavedProperties(@Param("id") long userID);
}
