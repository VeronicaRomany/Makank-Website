package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.SavedPostsEntry;
import mkanak_spring.model.entities.SavedPostsEntryID;
import mkanak_spring.model.repositories.PostRepo;
import mkanak_spring.model.repositories.SavedPostsRepo;
import mkanak_spring.model.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class SavedPostsRepoTest {
    @Autowired
    SavedPostsRepo savedPostsRepo ;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

//    @BeforeEach
//    void verifyUserPost() {
//        assertTrue(postRepo.existsById(1L));
//        assertTrue(userRepo.existsById(1L));
//    }
//
//    @Test
//    void testAddingSavedEntry() {
//        SavedPostsEntry entry = new SavedPostsEntry();
//        entry.setUserID(1L);
//        entry.setPostID(1L);
//
//        SavedPostsEntryID id = new SavedPostsEntryID();
//        id.setPostID(1L);
//        id.setUserID(1L);
//        savedPostsRepo.save(entry);
//        assertTrue(savedPostsRepo.existsById(id));
//        savedPostsRepo.deleteSavedPost(1L,1L);
//    }
//
//    @Test
//    void removeAddedSavedEntry(){
//        SavedPostsEntry entry = new SavedPostsEntry();
//        entry.setUserID(1L);
//        entry.setPostID(1L);
//
//        SavedPostsEntryID id = new SavedPostsEntryID();
//        id.setPostID(1L);
//        id.setUserID(1L);
//        savedPostsRepo.save(entry);
//        savedPostsRepo.deleteSavedPost(1L,1L);
//        assertFalse(savedPostsRepo.existsById(id));
//    }

    @Test
    void testRetreivingSavedPostsIDsContainTest(){
        SavedPostsEntry id = new SavedPostsEntry();
        id.setPostID(1L);
        id.setUserID(1L);
        savedPostsRepo.save(id);
        assertThat(savedPostsRepo.getUserSavedPostsIDs(1L).size()).isEqualTo(1);
    }
    @Test
    void testRetreivingSavedPostsIDEmptyTest(){
        SavedPostsEntry id = new SavedPostsEntry();
        id.setPostID(1L);
        id.setUserID(1L);
       // savedPostsRepo.save(id);
        assertThat(savedPostsRepo.getUserSavedPostsIDs(1L).size()).isEqualTo(0);
    }

    @Test
    void testRetreivingSeveralSavedPostsIDsContainTest(){
        SavedPostsEntry id = new SavedPostsEntry();
        id.setPostID(1L);
        id.setUserID(1L);
        savedPostsRepo.save(id);
        id.setPostID(2L);
        id.setUserID(1L);
        savedPostsRepo.save(id);
        List<Long> idList= new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        assertThat(savedPostsRepo.getUserSavedPostsIDs(1L)).isEqualTo(idList);
    }
}
