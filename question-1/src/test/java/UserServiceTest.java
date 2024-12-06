import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserById() {
        when(userRepository.findUserById(1)).thenReturn(new User(1, "John Doe"));

        User user = userService.findUserById(1);
        assertEquals("John Doe", user.getName());
        verify(userRepository, times(1)).findUserById(1);
    }
}
