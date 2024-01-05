package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Currency;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class DriverRatingTest {
    private Driver driver;
    private Route route;
    private DriverRating driverRating;
    private EmailAddress email;
    private Money money;
    private final Currency euroCurrency = Currency.getInstance("EUR");

    @BeforeEach
    public void setUp() {
        email = new EmailAddress("pappas@gmail.com");
        driver = new Driver("john123", "john", "pappas", "696949", email, "12345123", "19", "GR16272397", "GRE10230910290194"
                , "mersedes");
        money = new Money(10.0, euroCurrency);
        ZipCode zip = new ZipCode("16562",0.0,0.0);
        Address destination = new Address("mesogeiwn", "10", "athens", zip, "greece");
        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        driverRating = new DriverRating(driver, route, "4.5"," 3.2", "5.0");
    }

    @AfterEach
    public void tearDown() {
        driver = null;
        route = null;
        email = null;
        money = null;
        driverRating = null;
    }

    @Test
    public void testValidSecurityRating() {
        assertEquals(3.2f, driverRating.getSecurityRating());
    }

    @Test
    public void testValidPolitenessRating() {
        assertEquals(4.5f, driverRating.getPolitenessRating());
    }

    @Test
    public void testValidCleanlinessRating() {
        assertEquals(5.0f, driverRating.getCleanlinessRating());
    }

    @Test
    public void testSetValidRatings() {
        driverRating.setSecurityRating("2.5");
        assertEquals("2.5", driverRating.getSecurityRating());

        driverRating.setPolitenessRating("3.0");
        assertEquals("3.0", driverRating.getPolitenessRating());

        driverRating.setCleanlinessRating("3.0");
        assertEquals("3.0", driverRating.getCleanlinessRating());

    }

    @Test
    public void testSetInvalidRatings() {
        // Assert that setting ratings above the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> driverRating.setPolitenessRating("5.1"));
        assertThrows(IllegalArgumentException.class, () -> driverRating.setSecurityRating("5.2"));
        assertThrows(IllegalArgumentException.class, () -> driverRating.setCleanlinessRating("5.3"));

        // Assert that setting ratings below the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> driverRating.setPolitenessRating("-1.0"));
        assertThrows(IllegalArgumentException.class, () -> driverRating.setSecurityRating("-2.0"));
        assertThrows(IllegalArgumentException.class, () -> driverRating.setCleanlinessRating("-0.5"));

        // Add assertions to check the exception messages
        assertThrows(IllegalArgumentException.class, () -> driverRating.setPolitenessRating("5.1"),
                "Politeness rating must be between 0.0 and 5.0");
        assertThrows(IllegalArgumentException.class, () -> driverRating.setSecurityRating("-1.0"),
                "Security rating must be between 0.0 and 5.0");
        assertThrows(IllegalArgumentException.class, () -> driverRating.setCleanlinessRating("5.3"),
                "Cleanliness rating must be between 0.0 and 5.0");
    }

    @Test
    public void testAverageRating() {
        float expectedAverage = (4.5f + 3.2f + 5.0f) / 3;
        assertEquals(expectedAverage, driverRating.averageRating());
    }
}
