package mkanak_spring.model.services;
import static org.assertj.core.api.Assertions.assertThat;

import mkanak_spring.model.LoginManager;
import mkanak_spring.model.User;
import mkanak_spring.model.UserCredentials;
import mkanak_spring.model.dao.UserCredentialsRepo;
import mkanak_spring.model.dao.UserDAO;
import mkanak_spring.model.dao.UserRepo;
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

    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private UserServiceImpl userServiceTest;

    @Test
    void onLoginInvokeWithCorrectUserCredentials() {
        LoginManager loginManager = new LoginManager();
        String username = "lolo";
        String password = "password";
        UserCredentials userCredentialsTest = loginManager.SignInUser(username, password);
        userCredentialsTest.setUser_id(1L);


        userDAO.logInUser(userCredentialsTest);
        ArgumentCaptor<UserCredentials> userCredentialsArgumentCaptor = ArgumentCaptor.forClass(UserCredentials.class);

        verify(userDAO).logInUser(userCredentialsArgumentCaptor.capture());

        UserCredentials capturedCredentials = userCredentialsArgumentCaptor.getValue();

        assertThat(capturedCredentials).isEqualTo(userCredentialsTest);
    }

    @Test
    public void onSignUpInvokeWithCorrectUserCredentials() {
        User userTest = new User(null, "yara", "username",
                "yara@gmail.com", "", "", "password", "", null);

        userTest.setUserID(1L);

        userDAO.saveUser(userTest);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userDAO).saveUser(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(userTest);

    }
}