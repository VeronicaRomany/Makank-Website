package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.Property;
import mkanak_spring.model.entities.PropertyPicture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@RunWith(SpringRunner.class)
@DataJpaTest
class PropertyPictureRepoTest {
    private static Property property = new Property();

    @Autowired
    PropertyPictureRepo propertyPictureRepoTest;
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
        propertyPictureRepoTest.deleteAll();
    }

    @Test
    void deletePicturesByIdNonExistentIdTest() {
        propertyRepo.save(property);
        propertyPictureRepoTest.save(new PropertyPicture(1L, "null.jpg"));
        propertyPictureRepoTest.save(new PropertyPicture(1L, "pic.jpg"));
        assertThat(propertyPictureRepoTest.findAll().size()).isEqualTo(2);
        propertyPictureRepoTest.deletePicturesById(1L);
        assertThat(propertyPictureRepoTest.findAll().size()).isEqualTo(0);
    }
    @Test
    void deletePicturesByIdAssertionTest() {
        propertyRepo.save(property);
        propertyPictureRepoTest.save(new PropertyPicture(1L, "null.jpg"));
        propertyPictureRepoTest.save(new PropertyPicture(1L, "pic.jpg"));
        assertThat(propertyPictureRepoTest.findAll().size()).isEqualTo(2);
        propertyPictureRepoTest.deletePicturesById(null);
        assertThat(propertyPictureRepoTest.findAll().size()).isEqualTo(2);
    }

    @Test
    void getPropertyPicturesWhenNoneExistsTest() {
        propertyRepo.save(property);
        assertThat(propertyPictureRepoTest.getPropertyPictures(property.getPropertyID()).size()).isEqualTo(0);
    }
    @Test
    void getPropertyPicturesWhenOneExistsTest() {
        propertyRepo.save(property);
        propertyPictureRepoTest.save(new PropertyPicture(1L, "null.jpg"));
        List<String> picList = new ArrayList<>();
        picList.add("null.jpg");
        assertEquals(propertyPictureRepoTest.getPropertyPictures(1L), picList);
    }

    @Test
    void getPropertyPicturesWhenMoreThanOneExistsTest() {
        propertyRepo.save(property);
        propertyPictureRepoTest.save(new PropertyPicture(1L, "null.jpg"));
        propertyPictureRepoTest.save(new PropertyPicture(1L, "pic.jpg"));
        List<String> picList = new ArrayList<>();
        picList.add("null.jpg");
        picList.add("pic.jpg");
        assertEquals(propertyPictureRepoTest.getPropertyPictures(1L), picList);
    }
}