package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepoTest;

    @AfterEach
    void deleteAll() {
        userRepoTest.deleteAll();
    }

    @Test
    void checkIfUsernameExistsTest() {
        // given
        String username = "lolo";
        User user = new User(null, "yara", username,
                "yarahossam@gmail.com", "", "", "password", "", "01234567891");

        userRepoTest.save(user);

        // when
        Boolean isPresent = userRepoTest.findByUsername(username);

        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    void checkIfUsernameDoesNotExistTest() {
        // given
        String username = "lolo";

        // when
        Boolean isPresent = userRepoTest.findByUsername(username);

        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    void findExistingUserInfoByIdTest() {
        String username = "lolo";
        User user = new User(null, "yara", username,
                "yarahossam@gmail.com", "", "", "password", "", "01234567891");

        userRepoTest.save(user);
        User foundUser = userRepoTest.findAll().get(0);
        assertThat(userRepoTest.findUserInfoByUseId(foundUser.getUserID())).isEqualTo(foundUser);

    }
    @Test
    void findNonExistentUserInfoTest() {
        assertThat(userRepoTest.findUserInfoByUseId(1000L)).isNull();
    }

}