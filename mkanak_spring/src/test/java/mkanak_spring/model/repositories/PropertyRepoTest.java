package mkanak_spring.model.repositories;
import mkanak_spring.model.entities.Property;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class PropertyRepoTest {
    private static Property property = new Property();
    @Autowired
    PropertyRepo propertyRepo;

    @BeforeAll
    static void setUp() {
        property.setPropertyID(1L);
        property.setAddress("Smouha");
        property.setArea(150);
        property.setCity("Alex");
        property.setPrice(1200000);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setInfo(null);
        property.setHasPictures(false);
        property.setType("apartment");
    }

    @Test
    void updateWithNullTypeThrowsExceptionTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        assertThrows(DataIntegrityViolationException.class,  () ->propertyRepo.updateProperty(ID, 2,
                2, 100000, "Alex", "smouha", 120,
                false, null, null, false));
    }

    @Test
    void updateWithNullAddressThrowsExceptionTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        assertThrows(DataIntegrityViolationException.class,  () ->propertyRepo.updateProperty(ID, 2,
                2, 100000, "Alex", null, 120,
                false, null, "apartment", false));
    }

    @Test
    void updateWithNullCityThrowsExceptionTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        assertThrows(DataIntegrityViolationException.class,  () ->propertyRepo.updateProperty(ID, 2,
                2, 100000, null, "smouha", 120,
                false, null, "apartment", false));
    }
    @Test
    void updateWithNullInfoDoesNotThrowExceptionTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        assertDoesNotThrow(() ->propertyRepo.updateProperty(ID, 2,
                2, 100000, "Alex", "smouha", 120,
                false, null, "apartment", false));
    }
}
