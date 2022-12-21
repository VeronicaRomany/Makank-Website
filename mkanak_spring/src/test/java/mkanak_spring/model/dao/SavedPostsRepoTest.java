package mkanak_spring.model.dao;

import mkanak_spring.model.entities.SavedPostsEntry;
import mkanak_spring.model.entities.SavedPostsEntryID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
class SavedPostsRepoTest {
    @Autowired
    SavedPostsRepo savedPostsRepo ;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    @Test
    void verifyUsedPost() {
        assertTrue(postRepo.existsById(1L));
    }

    @Test
    void verifyUsedUser() {
        assertTrue(userRepo.existsById(1L));
    }

    @Test
    void testAddingSavedEntry() {
        SavedPostsEntry entry = new SavedPostsEntry();
        entry.setUserID(1L);
        entry.setPostID(1L);

        SavedPostsEntryID id = new SavedPostsEntryID();
        id.setPostID(1L);
        id.setUserID(1L);
        savedPostsRepo.save(entry);
        assertTrue(savedPostsRepo.existsById(id));
    }
//
//    @AfterEach
//    void re



}

/*

    @AfterEach
    void deleteAll() {
        userRepoTest.deleteAll();
    }

        userRepoTest.save(user);

        // when
        Boolean isPresent = userRepoTest.findByEmail(email);

        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    void checkIfUserEmailDoesntExist() {
        // given
        String email = "yarahossam@gmail.com";

        // when
        Boolean isPresent = userRepoTest.findByEmail(email);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void checkIfUsernameExists() {
        // given
        String username = "lolo";
        User user = new User(null, "yara", username,
                "yarahossam@gmail.com", "", "", "password", "", null);

        userRepoTest.save(user);

        // when
        Boolean isPresent = userRepoTest.findByUsername(username);

        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    void checkIfUsernameDoesntExist() {
        // given
        String username = "lolo";

        // when
        Boolean isPresent = userRepoTest.findByUsername(username);

        // then
        assertThat(isPresent).isFalse();
    }
}
 */