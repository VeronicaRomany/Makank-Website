package mkanak_spring.model.repositories;

import mkanak_spring.model.FilterPreference;
import mkanak_spring.model.SortingPreference;
import mkanak_spring.model.ViewingPreference;
import mkanak_spring.model.entities.Post;
import mkanak_spring.model.filters.PostSpecificationBuilder;
import mkanak_spring.model.repositories.PostRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@DataJpaTest
class PostRepoTest {
    @Autowired
    PostRepo postRepoTest;

    @Test
    void testInsertionSimple(){
        Post testPost = this.getTestPost(0);
        postRepoTest.save(testPost);
        boolean present = postRepoTest.existsByAddress("testAddress");
        assertTrue(present);
    }

    @Test
    void testInsertionThenDeletionSimple(){
        Post testPost = this.getTestPost(0);
        postRepoTest.save(testPost);
        postRepoTest.deleteByAddressAllIgnoreCase("testAddress");
        boolean present = postRepoTest.existsByAddress("testAddress");
        assertFalse(present);
    }

    @Test
    void testInsertionBatch(){
        ArrayList<Post> batch = new ArrayList<>();
        for(int i = 0 ; i<5 ;i++){
            batch.add(this.getTestPost(i));
        }
        postRepoTest.saveAll(batch);
        long num = postRepoTest.countByAddress("testAddress");
        assertEquals(num,batch.size());
    }


    @Test
    void testInsertionThenDeletionBatch(){
        ArrayList<Post> batch = new ArrayList<>();
        for(int i = 0 ; i<5 ;i++){
            batch.add(this.getTestPost(i));
        }
        postRepoTest.saveAll(batch);
        postRepoTest.deleteByAddressAllIgnoreCase("testAddress");
        long num = postRepoTest.countByAddress("testAddress");
        assertEquals(num,0);
    }


    @Test
    void testRetrievalFiltered(){
        ArrayList<Post> batch = new ArrayList<>();
        for(int i = 0 ; i<5 ;i++){
            batch.add(this.getTestPost(i));
        }

        postRepoTest.saveAll(batch);
        ViewingPreference v = this.getTestPreference();
        PostSpecificationBuilder sb = new PostSpecificationBuilder(v);
        List<Post> filteredPosts = postRepoTest.findAll(sb.build());
        assertEquals(1,filteredPosts.size());
    }

    @Test
    void testAutomaticIDs(){
        ArrayList<Post> batch = new ArrayList<>();
        for(int i = 0 ; i<5 ;i++){
            batch.add(this.getTestPost(i));
        }
        postRepoTest.saveAll(batch);
        List<Long> ids = postRepoTest.getDistinctPostIDsByAddress("testAddress");
        assertEquals(ids.size(),5);
    }

    @Test
    void testRetrievalByIDs(){
        ArrayList<Post> batch = new ArrayList<>();
        for(int i = 0 ; i<5 ;i++){
            batch.add(this.getTestPost(i));
        }
        postRepoTest.saveAll(batch);
        List<Long> ids = postRepoTest.getDistinctPostIDsByAddress("testAddress");
        List<Post> postsByIDs = postRepoTest.findAllById(ids);
        assertEquals(postsByIDs.size(),5);
    }


    @AfterEach
    void tearDown() {
        postRepoTest.deleteAll();
    }

























    private Post getTestPost(int i){
        Post p1 = new Post();
        p1.setSellerID(1);
        p1.setAddress("testAddress");
        p1.setCity("testCity" + i);
        p1.setArea(1);
        p1.setPrice(15);
        p1.setBathroomNumber(1);
        p1.setRent(true);
        p1.setType("villa");
        p1.setRoomNumber(5);
        p1.setHasPictures(false);

        System.out.println(p1.getCity());

        return p1;
    }


    private ViewingPreference getTestPreference(){
        ViewingPreference v = new ViewingPreference();
        v.setFiltered(true);
        v.setFilterPreference(new FilterPreference());
        v.setSortingPreference(new SortingPreference());
        v.setSorted(false);

        v.getFilterPreference().setInfoSearchWord("testAddress");
        v.getFilterPreference().setCitySearchWord("testCity3");

        return v;
    }
}