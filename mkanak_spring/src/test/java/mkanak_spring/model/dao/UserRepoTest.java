package mkanak_spring.model.dao;

import mkanak_spring.model.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

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
    void checkIfUserEmailExists() {
        // given
        String email = "yarahossam@gmail.com";
        User user = new User(null, "yara", "yara test",
                email, "", "", "password", "", null);

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