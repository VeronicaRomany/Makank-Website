package mkanak_spring.model.dao;

import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.SortingPreference;
import mkanak_spring.model.ViewingPreference;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
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