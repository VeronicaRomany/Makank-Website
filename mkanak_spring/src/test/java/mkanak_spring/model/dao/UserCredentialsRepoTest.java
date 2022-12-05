package mkanak_spring.model.dao;

import mkanak_spring.model.User;
import mkanak_spring.model.UserCredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class UserCredentialsRepoTest {

    @Autowired
        private UserCredentialsRepo userCredentialsRepoTest;

    @AfterEach
    void deleteAll() {
        userCredentialsRepoTest.deleteAll();
    }
    @Test
    void returnsCorrectUsernameWhenExists() {
        // given
        String username = "lolo";
        String password = "password";

        UserCredentials userCredentials = new UserCredentials(username, password);
        userCredentials.setUser_id(1L);

        userCredentialsRepoTest.save(userCredentials);

        // when
        UserCredentials userCredentialsRepoTestByUsername = userCredentialsRepoTest.findByUsername(username);


        // then
        assertThat(userCredentialsRepoTestByUsername.getUsername()).isEqualTo(username);
    }

    @Test
    void returnsCorrectPasswordForUserWhenExists() {
        // given
        String username = "lolo";
        String password = "password";

        UserCredentials userCredentials = new UserCredentials(username, password);
        userCredentials.setUser_id(1L);

        userCredentialsRepoTest.save(userCredentials);

        // when
        UserCredentials userCredentialsRepoTestByUsername = userCredentialsRepoTest.findByUsername(username);


        // then
        assertThat(userCredentialsRepoTestByUsername.getPassword()).isEqualTo(password);
    }

    @Test
    void returnsNullWhenUsernameDoesntExist() {
        // given
        String username = "lolo";
        String password = "password";

        UserCredentials userCredentials = new UserCredentials(username, password);
        userCredentials.setUser_id(1L);


        // when
        UserCredentials userCredentialsRepoTestByUsername = userCredentialsRepoTest.findByUsername(username);

        // then
        assertThat(userCredentialsRepoTestByUsername).isNull();
    }

}