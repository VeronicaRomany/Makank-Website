package mkanak_spring.model.repositories;

import jakarta.transaction.Transactional;
import mkanak_spring.model.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    @Transactional
    @Modifying
    @Query(value = "update property set rooms = ?2, bathrooms = ?3, price = ?4, city = ?5, property_address =?6," +
            " area = ?7, for_rent = ?8, info = ?9, property_type = ?10, has_pictures = ?11" +
            " where post_id = ?1", nativeQuery = true)
    void updateProperty(Long postID, int roomNumber, int bathroomNumber, int price, String city, String address,
                        int area, boolean forRent, String info, String propertyType, boolean hasPictures);
}
