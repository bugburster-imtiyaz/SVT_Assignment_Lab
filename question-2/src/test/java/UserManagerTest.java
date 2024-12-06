import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class UserManagerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserManager userManager;

    @Test
    void validUser() {

        MockitoAnnotations.openMocks(this);

        when(userService.usernameExists("validUser")).thenReturn(false);
        when(userService.saveUser("validUser", "password123")).thenReturn(true);

        boolean result = userManager.registerUser("validUser", "password123");


        assertTrue(result, "Registration successful!");
        verify(userService).usernameExists("validUser");
        verify(userService).saveUser("validUser", "password123");
    }

    @Test
    void UserExisting() {

        MockitoAnnotations.openMocks(this);

        when(userService.usernameExists("existingUser")).thenReturn(true);
        boolean result = userManager.registerUser("existingUser", "password123");

        assertFalse(result, "Failed");
        verify(userService).usernameExists("existingUser");
        verify(userService, never()).saveUser(anyString(), anyString());
    }

    @Test
    void RegisterUserSaveFailure() {

        MockitoAnnotations.openMocks(this);

        when(userService.usernameExists("validUser")).thenReturn(false);
        when(userService.saveUser("validUser", "password123")).thenReturn(false);
        boolean result = userManager.registerUser("validUser", "password123");


        assertFalse(result, "Fail!");
        verify(userService).usernameExists("validUser");
        verify(userService).saveUser("validUser", "password123");
    }
}