package mkanak_spring.model.dao;

import mkanak_spring.model.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//
//@RunWith(SpringRunner.class)
//@DataJpaTest
class PostDAOTest {

    @Autowired
    ApartmentRepo apartmentRepo;
    @Autowired
    VillaRepo villaRepo;
    @Autowired
    PropertyPictureRepo propertyPictureRepo;
    @Autowired
    PostRepo postRepo;
    @Autowired
    SavedPostsRepo savedPostsRepo;
    @Autowired
    PostDAO dao;
//
//
//    @BeforeEach
//    Void setup(){
//
//    }


    @Test
    void saveApartment() {
    }

    @Test
    void saveVilla() {
    }

    @Test
    void saveAllPictures() {
    }

    @Test
    void getAllPosts() {
    }

    @Test
    void getPostsByApartmentDetails() {
    }

    @Test
    void getPostsByUser() {
    }

    @Test
    void getSavedPostsByUserID() {


    }
}