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
class PostMinPriceSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testSmallMinPriceRetrieveAllThree(){
        //arrange
        postRepoTest.save(getDummyPost(1000,1));
        postRepoTest.save(getDummyPost(2000,2));
        postRepoTest.save(getDummyPost(3000,3));
        ViewingPreference v = this.getTestPreference(500);
        PostMinPriceSpecification sp = new PostMinPriceSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(3,retrieved.size());
    }

    @Test
    void testMiddlePriceRetrieveTwo(){
        //arrange
        postRepoTest.save(getDummyPost(1000,1));
        postRepoTest.save(getDummyPost(2000,2));
        postRepoTest.save(getDummyPost(3000,3));
        ViewingPreference v = this.getTestPreference(2000);
        PostMinPriceSpecification sp = new PostMinPriceSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testLargeMinPriceRetrieveNone(){
        //arrange
        postRepoTest.save(getDummyPost(1000,1));
        postRepoTest.save(getDummyPost(2000,2));
        postRepoTest.save(getDummyPost(3000,3));
        ViewingPreference v = this.getTestPreference(4000);
        PostMinPriceSpecification sp = new PostMinPriceSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }





    private Post getDummyPost(int price,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Gleem");
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(1L);
        property.setPrice(price);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setInfo(null);
        property.setHasPictures(false);
        property.setType("apartment");
        property.setImage(null);
        property.setSellerName("mido");
        return property;
    }


    private ViewingPreference getTestPreference(int minPrice){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        v.getFilterPreference().setMinPrice(minPrice);
        return v;
    }
}