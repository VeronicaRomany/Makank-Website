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
class PostAreaRangeSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testLargeAreaRangeRetrieveAllThree(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(0,40);
        PostAreaRangeSpecification sp = new PostAreaRangeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(3,retrieved.size());
    }

    @Test
    void testTightAreaRangeRetrieveMiddleOne(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(20,20);
        PostAreaRangeSpecification sp = new PostAreaRangeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testAreaRangeRetrieveNone(){
        //arrange
        postRepoTest.save(getDummyPost(10,1));
        postRepoTest.save(getDummyPost(20,2));
        postRepoTest.save(getDummyPost(30,3));
        ViewingPreference v = this.getTestPreference(40,90);
        PostAreaRangeSpecification sp = new PostAreaRangeSpecification(v);
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


    private ViewingPreference getTestPreference(int minArea,int maxArea){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setMaxArea(maxArea);
        v.getFilterPreference().setMinArea(minArea);
        return v;
    }
}