package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.Property;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
class VillaRepoTest {
    private static Property property = new Property();

    @Autowired
    VillaRepo villaRepoTest;
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
        property.setType("villa");
    }

    @AfterEach
    void tearDown() {
        villaRepoTest.deleteAll();
    }

    @Test
    void insertVillaHappyCaseTest() {
        propertyRepo.save(property);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(0);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        villaRepoTest.insertVilla(ID, true, 8, false);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(1);
    }
    @Test
    void insertVillaWrongIdTest() {
        propertyRepo.save(property);
        assertThrows(DataIntegrityViolationException.class,  () ->villaRepoTest.insertVilla(50L, true, 8, false));
    }

    @Test
    void deleteVillaHappyCaseTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        villaRepoTest.insertVilla(ID, true, 8, false);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(1);
        villaRepoTest.deleteVilla(ID);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteVillaNonExistentIdTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        villaRepoTest.insertVilla(ID, true, 8, false);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(1);
        villaRepoTest.deleteVilla(50L);
        assertThat(villaRepoTest.findAll().size()).isEqualTo(1);
    }

}