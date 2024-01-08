package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Currency;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class DriverRatingTest {
    private Driver driver;

    private Passenger passenger;
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
        ZipCode zip = new ZipCode("16562");
        Address destination = new Address("mesogeiwn", "10", "athens", zip, "greece");
        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        driverRating = new DriverRating(driver, route, "4.5"," 3.2", "5.0");

        passenger= new Passenger("eleniz","eleni","Zanou","6977292186",email,"2003","20","1023091029099", "Eleni Zanou", "333");

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
        assertEquals(" 3.2", driverRating.getSecurityRating());
    }

    @Test
    public void testValidPolitenessRating() {
        assertEquals("4.5", driverRating.getPolitenessRating());
    }

    @Test
    public void testValidCleanlinessRating() {
        assertEquals("5.0", driverRating.getCleanlinessRating());
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
    public void TestAddRate(){
        driverRating.addRate(passenger,driverRating);
        assertTrue(driverRating.hasRate(driverRating));
        assertTrue(driverRating.getPassengersRates().containsValue(driverRating));
    }
    @Test
    public void TestArguments(){
        assertEquals(route, driverRating.getRoute());
        assertEquals(driver, driverRating.getDriver());
    }

    @Test
    public void testAverageRating() {
        float expectedAverage = (4.5f + 3.2f + 5.0f) / 3;
        assertEquals(expectedAverage, driverRating.averageRating());
    }
}
