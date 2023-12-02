import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PassengerRatingTest {
    private Passenger passenger;
    private Route route;
    private PassengerRating passengerRating;

    @BeforeEach
    public void setUp() {
        passenger = new Passenger(); 
        route = new Route(); 
        passengerRating = new PassengerRating(passenger, route, 0.0f, 0.0f, 0.0f);
    }

    @AfterEach
    public void tearDown() {
        passenger = null;
        route = null;
        driverRating = null;
    }

    @Test
    public void testGetSetConsistencyRating() {
        passengerRating.setConsistencyRating(4.5f);
        assertEquals(4.5f, passengerRating.getSecurityRating(),0.001);
    }

    @Test
    public void testGetSetPolitenessRating() {
        passengerRating.setPolitenessRating(3.2f);
        assertEquals(3.2f, passengerRating.getPolitenessRating(),0.001);
    }

    @Test
    public void testGetSetReliabilityRating() {
        passengerRating.setReliabilityRating(5.0f);
        assertEquals(5.0f, passengerRating.getReliabilityRating(),0.001);
    }

    public void testSetInvalidRatings() {
        // Assert that setting ratings above the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setPolitenessRating(5.1f));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setConsistencyRating(5.2f));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setReliabilityRating(5.3f));
    
        // Assert that setting ratings below the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setPolitenessRating(-1.0f));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setConsistencyRating(-2.0f));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setReliabilityRating(-0.5f));
    }
}
