package mkanak_spring.model.dao;

import mkanak_spring.model.entities.SavedPostsEntry;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class SavedPostsRepoTest {
    @Autowired
    private SavedPostsRepo repo ;

    @Test
    void getUserSavedPostsIDs() {
        long userID = 11111;
        long postID = 44;
        SavedPostsEntry ent = new SavedPostsEntry();
        ent.setUserID(userID);
        ent.setPostID(postID);
        repo.save(ent);

        List<Long> ids = repo.getUserSavedPostsIDs(userID);
        assertTrue(ids.contains(postID));
    }

    @Test
    void deleteSavedPost() {

    }
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