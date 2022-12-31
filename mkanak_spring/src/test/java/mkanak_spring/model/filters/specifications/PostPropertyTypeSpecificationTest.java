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
class PostPropertyTypeSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testInsertingTwoSimilarAndRetrievingTwo(){
        //arrange
        postRepoTest.save(getDummyPost("apartment",1));
        postRepoTest.save(getDummyPost("apartment",2));
        ViewingPreference v = this.getTestPreference("apartment");
        PostPropertyTypeSpecification sp = new PostPropertyTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testInsertingTwoSimilarAndRetrievingTwoNotExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("apartment",1));
        postRepoTest.save(getDummyPost("apartment",2));
        ViewingPreference v = this.getTestPreference("apaRtment");
        PostPropertyTypeSpecification sp = new PostPropertyTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testInsertingTwoDifferentAndRetrievingOne(){
        //arrange
        postRepoTest.save(getDummyPost("apartment",1));
        postRepoTest.save(getDummyPost("villa",2));
        ViewingPreference v = this.getTestPreference("villa");
        PostPropertyTypeSpecification sp = new PostPropertyTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }


    @Test
    void testInsertingTwoSimilarAndRetrievingNone(){
        //arrange
        postRepoTest.save(getDummyPost("apartment",1));
        postRepoTest.save(getDummyPost("apartment",2));
        ViewingPreference v = this.getTestPreference("villa");
        PostPropertyTypeSpecification sp = new PostPropertyTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }






    private Post getDummyPost(String type,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Street");
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(1L);
        property.setPrice(1200000);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setInfo(null);
        property.setHasPictures(false);
        property.setType(type);
        property.setImage(null);
        property.setSellerName("mido");
        return property;
    }


    private ViewingPreference getTestPreference(String type){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setPropertyType(type);
        return v;
    }
}