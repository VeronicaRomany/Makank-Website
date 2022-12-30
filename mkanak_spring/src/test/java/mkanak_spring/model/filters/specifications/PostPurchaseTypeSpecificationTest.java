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
class PostPurchaseTypeSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testRetrievingTwoRents(){
        //arrange
        postRepoTest.save(getDummyPost(true,1));
        postRepoTest.save(getDummyPost(true,2));
        ViewingPreference v = this.getTestPreference(true);
        PostPurchaseTypeSpecification sp = new PostPurchaseTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }

    @Test
    void testRetrievingOneRent(){
        //arrange
        postRepoTest.save(getDummyPost(true,1));
        postRepoTest.save(getDummyPost(false,2));
        ViewingPreference v = this.getTestPreference(true);
        PostPurchaseTypeSpecification sp = new PostPurchaseTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testRetrievingOneRentsBuy(){
        //arrange
        postRepoTest.save(getDummyPost(true,1));
        postRepoTest.save(getDummyPost(false,2));
        ViewingPreference v = this.getTestPreference(false);
        PostPurchaseTypeSpecification sp = new PostPurchaseTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testRetrievingNoRents(){
        //arrange
        postRepoTest.save(getDummyPost(false,1));
        postRepoTest.save(getDummyPost(false,2));
        ViewingPreference v = this.getTestPreference(true);
        PostPurchaseTypeSpecification sp = new PostPurchaseTypeSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }

    private Post getDummyPost(boolean rent,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Street");
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(1L);
        property.setPrice(1200000);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setRent(rent);
        property.setInfo(null);
        property.setHasPictures(false);
        property.setType("apartment");
        property.setImage(null);
        property.setSellerName("mido");
        return property;
    }


    private ViewingPreference getTestPreference(boolean rent){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);
        String choice = "";
        if(rent) choice="rent";
        else choice = "buy";
        v.getFilterPreference().setPurchaseChoice(choice);
        return v;
    }
}