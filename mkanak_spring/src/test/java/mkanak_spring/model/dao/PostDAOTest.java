package mkanak_spring.model.dao;

import mkanak_spring.model.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

//
//@RunWith(SpringRunner.class)
//@DataJpaTest
class PostDAOTest {

    @Mock
    ApartmentRepo apartmentRepo;
    @Mock
    VillaRepo villaRepo;
    @Mock
    PropertyPictureRepo propertyPictureRepo;
    @Mock
    PostRepo postRepo;
    @Mock
    SavedPostsRepo savedPostsRepo;
    @Autowired
    PostDAO dao;


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