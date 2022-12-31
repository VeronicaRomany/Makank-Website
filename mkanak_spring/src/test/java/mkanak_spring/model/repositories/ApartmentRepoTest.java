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
class ApartmentRepoTest {
    private static Property property = new Property();

    @Autowired
    ApartmentRepo apartmentRepoTest;
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

    @AfterEach
    void tearDown() {
        apartmentRepoTest.deleteAll();
    }

    @Test
    void insertApartmentHappyCaseTest() {
        propertyRepo.save(property);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(0);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, false);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(1);
    }
    @Test
    void insertApartmentWrongIdTest() {
        propertyRepo.save(property);
        assertThrows(DataIntegrityViolationException.class,  () ->apartmentRepoTest.insertApartment(50L, true, 8, false));
    }

    @Test
    void deleteApartmentHappyCaseTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, false);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(1);
        apartmentRepoTest.deleteApartment(ID);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteApartmentNonExistentIdTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, false);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(1);
        apartmentRepoTest.deleteApartment(50L);
        assertThat(apartmentRepoTest.findAll().size()).isEqualTo(1);
    }

    @Test
    void getStudentHousingIDsReturnsZeroWhenNoneExistsTest() {
        propertyRepo.save(property);
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, false);
        apartmentRepoTest.insertApartment(ID+1, true, 8, false);
        assertThat(apartmentRepoTest.getStudentHousingIDs(true).size()).isEqualTo(0);
    }

    @Test
    void getStudentHousingIDsReturnsOneWhenExistsTest() {
        propertyRepo.save(property);
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, true);
        apartmentRepoTest.insertApartment(ID+1, true, 8, false);
        assertThat(apartmentRepoTest.getStudentHousingIDs(true).size()).isEqualTo(1);
    }
    @Test
    void getStudentHousingIDsReturnsTwoWhenBothExistTest() {
        propertyRepo.save(property);
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, true);
        apartmentRepoTest.insertApartment(ID+1, true, 8, true);
        assertThat(apartmentRepoTest.getStudentHousingIDs(true).size()).isEqualTo(2);
    }

    @Test
    void getStudentHousingIDsAssertCorrectReturnedPostIdTest() {
        propertyRepo.save(property);
        Long ID = propertyRepo.findAll().get(0).getPropertyID();
        apartmentRepoTest.insertApartment(ID, true, 8, true);
        assertThat(apartmentRepoTest.getStudentHousingIDs(true).get(0)).isEqualTo(ID);
    }

}