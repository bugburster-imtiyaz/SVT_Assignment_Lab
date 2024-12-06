import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class OrderServiceTest {

    @Mock
    private ShippingService mockShippingService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_ValidShipment() {
        when(mockShippingService.ship("item1", 5)).thenReturn(true);

        boolean result = orderService.placeOrder("item1", 5);


        assertTrue(result, "Expected placeOrder to return true for valid shipments");
        verify(mockShippingService).ship("item1", 5);
    }

    @Test
    void testPlaceOrder_InvalidShipment() {
        when(mockShippingService.ship("item1", 0)).thenReturn(false);

        boolean result = orderService.placeOrder("item1", 0);

        assertFalse(result, "Expected placeOrder to return false for invalid shipments");
        verify(mockShippingService).ship("item1", 0);
    }

    @Test
    void testPlaceOrder_InvalidQuantity() {
        boolean result = orderService.placeOrder("item1", -1);

        assertFalse(result, "Expected placeOrder to return false for invalid quantity");
        verify(mockShippingService, never()).ship(anyString(), anyInt());
    }
}
