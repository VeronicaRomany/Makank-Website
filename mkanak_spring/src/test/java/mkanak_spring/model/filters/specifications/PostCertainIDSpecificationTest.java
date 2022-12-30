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
class PostCertainIDSpecificationTest {
    @Autowired
    PostRepo postRepoTest;


    @Test
    void testRetrievePostsByUser(){
        //arrange
        postRepoTest.save(getDummyPost(1,1));
        postRepoTest.save(getDummyPost(1,2));
        postRepoTest.save(getDummyPost(1,3));
        postRepoTest.save(getDummyPost(1,4));
        postRepoTest.save(getDummyPost(1,5));
        postRepoTest.save(getDummyPost(2,6));
        postRepoTest.save(getDummyPost(2,7));

        ViewingPreference v = this.getDummyTestPreference();
        PostCertainIDSpecification sp = new PostCertainIDSpecification(v,1);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(5,retrieved.size());
    }


    @Test
    void testRetrieveNoPostsByUser(){
        //arrange
        postRepoTest.save(getDummyPost(2,6));
        postRepoTest.save(getDummyPost(2,7));

        ViewingPreference v = this.getDummyTestPreference();
        PostCertainIDSpecification sp = new PostCertainIDSpecification(v,1);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }



    private Post getDummyPost(int sellerID,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Gleem");
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(sellerID);
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