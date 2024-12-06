import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginManagerTest {

    private LoginManager.AuthenticationService mockAuthenticationService;
    private LoginManager loginManager;

    @BeforeEach
    void setUp() {

        mockAuthenticationService = mock(LoginManager.AuthenticationService.class);
        loginManager = new LoginManager(mockAuthenticationService);
    }

    @Test
    void testLogin_ValidCredentials() throws Exception {
        when(mockAuthenticationService.authenticate("validUser", "validPass")).thenReturn(true);

        boolean result = loginManager.login("validUser", "validPass");
        assertTrue(result, "Login should succeed for valid credentials");
        verify(mockAuthenticationService).authenticate("validUser", "validPass");
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        when(mockAuthenticationService.authenticate("invalidUser", "wrongPass")).thenReturn(false);

        boolean result = loginManager.login("invalidUser", "wrongPass");

        assertFalse(result, "Login should fail for invalid credentials");
        verify(mockAuthenticationService).authenticate("invalidUser", "wrongPass");
    }

    @Test
    void testLogin_NullUsernameOrPassword() {

        Exception usernameException = assertThrows(Exception.class,
                () -> loginManager.login(null, "somePass"),
                "Expected an exception for null username"
        );
        assertEquals("Username and password cannot be null", usernameException.getMessage());

        Exception passwordException = assertThrows(Exception.class,
                () -> loginManager.login("someUser", null),
                "Expected an exception for null password"
        );
        assertEquals("Username and password cannot be null", passwordException.getMessage());
    }

    @Test
    void testAuthenticateMethodCalled() throws Exception {

        when(mockAuthenticationService.authenticate("testUser", "testPass")).thenReturn(true);

        loginManager.login("testUser", "testPass");
        verify(mockAuthenticationService).authenticate("testUser", "testPass");
    }
}
