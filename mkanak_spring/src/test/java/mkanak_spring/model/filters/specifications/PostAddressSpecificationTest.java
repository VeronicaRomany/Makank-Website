package mkanak_spring.model.filters.specifications;

import mkanak_spring.model.entities.Apartment;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.entities.Property;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.preferences.FilterPreference;
import mkanak_spring.model.preferences.SortingPreference;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class PostAddressSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testInsertingTwoSimilarAndRetrievingTwo(){
        //arrange
        postRepoTest.save(getDummyPost("Smouha",1));
        postRepoTest.save(getDummyPost("Smouha",2));
        ViewingPreference v = this.getTestPreference("Smouha");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }



    @Test
    void testInsertingTwoDifferentAndRetrievingOne(){
        //arrange
        postRepoTest.save(getDummyPost("Smouha",1));
        postRepoTest.save(getDummyPost("Gleem",2));
        ViewingPreference v = this.getTestPreference("Smouha");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }



    @Test
    void testInsertingTwoSimilarAndRetrievingNone(){
        //arrange
        postRepoTest.save(getDummyPost("Smouha",1));
        postRepoTest.save(getDummyPost("Smouha",2));
        ViewingPreference v = this.getTestPreference("Sidi beshr");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }


    @Test
    void testInsertingOneComplexSentenceAndSearchingByAWordExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("3and Smouha ganb mac",1));
        ViewingPreference v = this.getTestPreference("Smouha");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testInsertingOneComplexSentenceAndSearchingByAWordNotExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("3and smouha ganb mac",1));
        ViewingPreference v = this.getTestPreference("Smouha");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testInsertingOneComplexSentenceAndSearchingByTwoWords(){
        //arrange
        postRepoTest.save(getDummyPost("3and smouha ganb mac",1));
        ViewingPreference v = this.getTestPreference("ganb mac");
        PostAddressSpecification sp = new PostAddressSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }


    private Post getDummyPost(String address,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress(address);
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(1L);
        property.setPrice(1200000);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setInfo(null);
        property.setHasPictures(false);
        property.setType("apartment");
        property.setImage(null);
        property.setSellerName("mido");
        return property;
    }


    private ViewingPreference getTestPreference(String searchWord){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setInfoSearchWord(searchWord);
        return v;
    }

}