package mkanak_spring.model.filters.specifications;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.preferences.FilterPreference;
import mkanak_spring.model.preferences.SortingPreference;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.repositories.PostRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostCitySpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testInsertingTwoSimilarAndRetrievingTwo(){
        //arrange
        postRepoTest.save(getDummyPost("Cairo",1));
        postRepoTest.save(getDummyPost("Cairo",2));
        ViewingPreference v = this.getTestPreference("Cairo");
        PostCitySpecification sp = new PostCitySpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }
    @Test
    void testInsertingTwoNotExactSimilarAndRetrievingTwo(){
        //arrange
        postRepoTest.save(getDummyPost("Cairo",1));
        postRepoTest.save(getDummyPost("Cairo",2));
        ViewingPreference v = this.getTestPreference("caIRo");
        PostCitySpecification sp = new PostCitySpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testInsertingTwoSimilarAndRetrievingNone(){
        //arrange
        postRepoTest.save(getDummyPost("Cairo",1));
        postRepoTest.save(getDummyPost("Cairo",2));
        ViewingPreference v = this.getTestPreference("Alex");
        PostCitySpecification sp = new PostCitySpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }


    @Test
    void testInsertingTwoDifferentAndRetrievingOne(){
        //arrange
        postRepoTest.save(getDummyPost("Cairo",1));
        postRepoTest.save(getDummyPost("Alex",2));
        ViewingPreference v = this.getTestPreference("Alex");
        PostCitySpecification sp = new PostCitySpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }


    @Test
    void testInsertingTwoDifferentAndRetrievingOneNotExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("Cairo",1));
        postRepoTest.save(getDummyPost("Alex",2));
        ViewingPreference v = this.getTestPreference("alEX");
        PostCitySpecification sp = new PostCitySpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    private Post getDummyPost(String city,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Street");
        property.setArea(150);
        property.setCity(city);
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


    private ViewingPreference getTestPreference(String city){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setCitySearchWord(city);
        return v;
    }
}