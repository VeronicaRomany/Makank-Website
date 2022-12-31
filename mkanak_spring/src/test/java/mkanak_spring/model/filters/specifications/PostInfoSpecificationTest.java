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
class PostInfoSpecificationTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testInsertingTwoSimilarAndRetrievingTwo(){
        //arrange
        postRepoTest.save(getDummyPost("New Building",1));
        postRepoTest.save(getDummyPost("New Building",2));
        ViewingPreference v = this.getTestPreference("New Building");
        PostInfoSpecification sp = new PostInfoSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(2,retrieved.size());
    }



    @Test
    void testInsertingTwoDifferentAndRetrievingOne(){
        //arrange
        postRepoTest.save(getDummyPost("New Building",1));
        postRepoTest.save(getDummyPost("old Building",2));
        ViewingPreference v = this.getTestPreference("New Building");
        PostInfoSpecification sp = new PostInfoSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }



    @Test
    void testInsertingTwoSimilarAndRetrievingNone(){
        //arrange
        postRepoTest.save(getDummyPost("New Building",1));
        postRepoTest.save(getDummyPost("New Building",2));
        ViewingPreference v = this.getTestPreference("old Building");
        PostInfoSpecification sp = new PostInfoSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(0,retrieved.size());
    }


    @Test
    void testInsertingOneComplexSentenceAndSearchingByAWordExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("New Building with very good view to the sea",1));
        ViewingPreference v = this.getTestPreference("New Building");
        PostInfoSpecification sp = new PostInfoSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }

    @Test
    void testInsertingOneComplexSentenceAndSearchingByAWordNotExactlySimilar(){
        //arrange
        postRepoTest.save(getDummyPost("New Building with very good view to the sea",1));
        ViewingPreference v = this.getTestPreference("nEw builDing");
        PostInfoSpecification sp = new PostInfoSpecification(v);
        //act
        List<Post> retrieved = postRepoTest.findAll(sp);
        //assert
        assertEquals(1,retrieved.size());
    }




    private Post getDummyPost(String info,int id){
        Post property=new Post();
        property.setPropertyID(id);
        property.setAddress("Gleem");
        property.setArea(150);
        property.setCity("Alex");
        property.setSellerID(1L);
        property.setPrice(1200000);
        property.setBathroomNumber(2);
        property.setRoomNumber(3);
        property.setInfo(info);
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