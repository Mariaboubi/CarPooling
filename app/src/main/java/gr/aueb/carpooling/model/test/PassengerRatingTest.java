package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Currency;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class PassengerRatingTest {
    private Passenger passenger;
    private Route route;
    private Driver driver;
    private PassengerRating passengerRating;
    private final Currency euroCurrency = Currency.getInstance("EUR");

    @BeforeEach
    public void setUp() {
        EmailAddress email = new EmailAddress("pappas@gmail.com");
        EmailAddress email2 = new EmailAddress("pappadia@gmail.com");
        passenger = new Passenger("john123", "john", "pappas", "6969497299", email, "12345123", "19",
                "12222", "john", "322");
        driver = new Driver("maria123", "maria", "pappas", "69694559", email2, "12345124", "19", "GRE10230910290194",
                "112233", "mersedes");
        Money money = new Money(10.0, euroCurrency);
        Address destination = new Address("mesogeiwn", "10", "athens", new ZipCode("16562",0.0,0.0), "greece");
        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        passengerRating = new PassengerRating(passenger, route, "4.5", "3.2", "5.0");
    }

    @AfterEach
    public void tearDown() {
        passenger = null;
        route = null;
        driver = null;
        passengerRating = null;
    }

    @Test
    public void testConstructorValidRatings() {
        assertEquals("4.5", passengerRating.getPolitenessRating());
        assertEquals("3.2", passengerRating.getConsistencyRating());
        assertEquals("5.0", passengerRating.getReliabilityRating());
    }

    @Test
    public void testConstructorInvalidRatings() {
        // Assert that the constructor throws IllegalArgumentException for invalid ratings
        assertThrows(IllegalArgumentException.class, () -> {
            new PassengerRating(passenger, route, "4.5", "5.2", "5.0");
        });
    }

    @Test
    public void testSetGetConsistencyRating() {
        passengerRating.setConsistencyRating("2.5");
        assertEquals("2.5", passengerRating.getConsistencyRating());
    }

    @Test
    public void testSetGetPolitenessRating() {
        passengerRating.setPolitenessRating("4.1");
        assertEquals("4.1", passengerRating.getPolitenessRating());
    }

    @Test
    public void testSetGetReliabilityRating() {
        passengerRating.setReliabilityRating("1.5");
        assertEquals("1.5", passengerRating.getReliabilityRating());
    }

    @Test
    public void testSetInvalidRatings() {
        // Assert that setting ratings above the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setPolitenessRating("5.1"));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setConsistencyRating("5.2"));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setReliabilityRating("5.3"));

        // Assert that setting ratings below the expected range is not allowed
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setPolitenessRating("-1.0"));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setConsistencyRating("-2.0"));
        assertThrows(IllegalArgumentException.class, () -> passengerRating.setReliabilityRating("-0.5f"));
    }

    @Test
    public void testAverageRating() {
        float expectedAverageRating = (4.5f + 3.2f + 5.0f) / 3;
        assertEquals(expectedAverageRating, passengerRating.averageRating(), 0.001);
    }
}
