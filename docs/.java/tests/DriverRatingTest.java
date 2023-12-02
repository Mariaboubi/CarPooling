package carPooling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DriverRatingTest {

    private Driver driver;
    private Route route;
    private DriverRating driverRating;

    @BeforeEach
    public void setUp() {
        driver = new Driver(); 
        route = new Route(); 
        driverRating = new DriverRating(driver, route, 0.0f, 0.0f, 0.0f);
    }

    @AfterEach
    public void tearDown() {
        driver = null;
        route = null;
        driverRating = null;
    }

    @Test
    public void testGetSetSecurityRating() {
        driverRating.setSecurityRating(4.5f);
        assertEquals(4.5f, driverRating.getSecurityRating(),0.001);
    }

    @Test
    public void testGetSetPolitenessRating() {
        driverRating.setPolitenessRating(3.2f);
        assertEquals(3.2f, driverRating.getPolitenessRating(),0.001);
    }

    @Test
    public void testGetSetCleanlinessRating() {
        driverRating.setCleanlinessRating(5.0f);
        assertEquals(5.0f, driverRating.getCleanlinessRating(),0.001);
    }
    
}
