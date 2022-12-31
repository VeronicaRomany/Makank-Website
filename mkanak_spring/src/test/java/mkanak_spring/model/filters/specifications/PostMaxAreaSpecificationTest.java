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
class PostMaxAreaSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testLargeMaxAreaRetrieveAllThree(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(50);
        PostMaxAreaSpecification sp = new PostMaxAreaSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(3,retrieved.size());
    }

    @Test
    void testMiddleAreaRetrieveTwo(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(21);
        PostMaxAreaSpecification sp = new PostMaxAreaSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testLowMaxAreaRetrieveNone(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(1);
        PostMaxAreaSpecification sp = new PostMaxAreaSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }





    private Post getDummyPost(int area,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Gleem");
        property.setArea(area);
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


    private ViewingPreference getTestPreference(int maxArea){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setMaxArea(maxArea);
        return v;
    }
}