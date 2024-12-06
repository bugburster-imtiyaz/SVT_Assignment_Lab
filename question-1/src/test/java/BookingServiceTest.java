import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BookingServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private BookingService bookingService;

    BookingServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessPayment() {
        bookingService.bookService();
        verify(paymentService, times(1)).processPayment();
    }
}