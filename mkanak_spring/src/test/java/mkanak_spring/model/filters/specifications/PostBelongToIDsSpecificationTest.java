package mkanak_spring.model.filters.specifications;

import mkanak_spring.model.entities.Post;
import mkanak_spring.model.preferences.ViewingPreference;
import mkanak_spring.model.repositories.PostRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostBelongToIDsSpecificationTest {
    @Autowired
    PostRepo postRepoTest;


    @Test
    void testRetrievePostsByUser(){
        //arrange
        postRepoTest.save(getDummyPost(1));
        postRepoTest.save(getDummyPost(2));
        postRepoTest.save(getDummyPost(3));
        postRepoTest.save(getDummyPost(4));
        postRepoTest.save(getDummyPost(5));
        postRepoTest.save(getDummyPost(6));
        postRepoTest.save(getDummyPost(7));
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(5L);
        ids.add(6L);
        ids.add(4L);
        ViewingPreference v = this.getDummyTestPreference();
        PostBelongToIDsSpecification sp = new PostBelongToIDsSpecification(v,ids);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(4,retrieved.size());
    }

    @Test
    void testRetrieveNoPostsByUser(){
        //arrange
        postRepoTest.save(getDummyPost(1));
        postRepoTest.save(getDummyPost(2));
        postRepoTest.save(getDummyPost(3));
        postRepoTest.save(getDummyPost(4));
        postRepoTest.save(getDummyPost(5));
        postRepoTest.save(getDummyPost(6));
        postRepoTest.save(getDummyPost(7));
        List<Long> ids = new ArrayList<>();
        ids.add(10L);
        ids.add(50L);
        ids.add(60L);
        ids.add(40L);
        ViewingPreference v = this.getDummyTestPreference();
        PostBelongToIDsSpecification sp = new PostBelongToIDsSpecification(v,ids);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }


    private Post getDummyPost(int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Gleem");
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


    private ViewingPreference getDummyTestPreference(){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(false);
        v.setSorted(false);
        return v;
    }
}