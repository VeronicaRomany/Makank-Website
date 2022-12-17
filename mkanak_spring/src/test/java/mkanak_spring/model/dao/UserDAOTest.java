package mkanak_spring.model.dao;

import mkanak_spring.model.LoginManager;
import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserDAOTest {

    @Mock
    private UserRepo userRepoTest;
    @Mock
    private UserCredentialsRepo userCredentialsRepoTest;

    @InjectMocks
    private UserDAO userDAOTest;
    @Test
    void onSaveUserInvokeWithCorrectUser() {
        User userTest = new User(null, "yara", "username",
                "yara@gmail.com", "", "", "password", "", null);
        userTest.setUserID(1L);
        userRepoTest.save(userTest);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepoTest).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(userTest);
    }

    @Test
    void onLogInUserInvokeWithCorrectUserCredentials() {
        LoginManager loginManager = new LoginManager();
        String username = "lolo";
        String password = "password";
        UserCredentials userCredentialsTest = loginManager.SignInUser(username, password);
        userCredentialsTest.setUserID(1L);

        userCredentialsRepoTest.findByUsername(userCredentialsTest.getUsername());

        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userCredentialsRepoTest).findByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();

        assertThat(capturedUsername).isEqualTo(username);

    }

    @Test
    void onUsernameExistsInvokeWithCorrectUsername() {
        String username = "lolo";

        userCredentialsRepoTest.findByUsername(username);

        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userCredentialsRepoTest).findByUsername(userArgumentCaptor.capture());

        String capturedUsername = userArgumentCaptor.getValue();

        assertThat(capturedUsername).isEqualTo(username);
    }

    @Test
    void onEmailExistsInvokeWithCorrectUsername() {
        String email = "email@yahoo.com";

        userRepoTest.findByEmail(email);

        ArgumentCaptor<String> userArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userRepoTest).findByEmail(userArgumentCaptor.capture());

        String capturedEmail = userArgumentCaptor.getValue();

        assertThat(capturedEmail).isEqualTo(email);

    }
}