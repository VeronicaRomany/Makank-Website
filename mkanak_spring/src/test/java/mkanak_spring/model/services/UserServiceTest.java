package mkanak_spring.model.services;

import static org.assertj.core.api.Assertions.assertThat;

import mkanak_spring.model.entities.User;
import mkanak_spring.model.entities.UserCredentials;
import mkanak_spring.model.repositories.UserCredentialsRepo;
import mkanak_spring.model.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserServiceTest {

    @Mock
    private UserRepo userRepoTest;
    @Mock
    private UserCredentialsRepo userCredentialsRepoTest;

    @InjectMocks
    private UserServiceImpl userServiceTest;

    @Test
    void onLoginInvokeWithCorrectUserCredentials() {
        JsonToObject loginManager = new JsonToObject();
        String username = "lolo";
        String password = "password";
        UserCredentials userCredentialsTest = loginManager.SignInUser(username, password);
        userCredentialsTest.setUserID(1L);

        userServiceTest.logInUser(userCredentialsTest);
        ArgumentCaptor<String> userCredentialsArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(userCredentialsRepoTest).findByUsername(userCredentialsArgumentCaptor.capture());

        String capturedCredentials = userCredentialsArgumentCaptor.getValue();

        assertThat(capturedCredentials).isEqualTo(username);
    }

    @Test
    public void onSignUpInvokeWithCorrectUserCredentials() {
        User userTest = new User(null, "yara", "username",
                "yara@gmail.com", "", "", "password", "", "01234567891");

        userTest.setUserID(1L);

        userServiceTest.saveUser(userTest);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userRepoTest).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getUsername()).isEqualTo(userTest.getUsername());
    }
}
