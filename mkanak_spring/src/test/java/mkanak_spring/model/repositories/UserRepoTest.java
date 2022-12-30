package mkanak_spring.model.repositories;

import mkanak_spring.model.entities.User;
import mkanak_spring.model.repositories.UserRepo;
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