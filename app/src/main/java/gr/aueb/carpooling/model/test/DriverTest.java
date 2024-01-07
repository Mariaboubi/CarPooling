package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


public class DriverTest {
    private Driver driver;
    private Route route;
    private Route route1;
    private EmailAddress email;
    private Address destination;
    private DriverRating rate;
    private final Currency euroCurrency = Currency.getInstance("EUR");

    @BeforeEach
    public void setUp() {
        email = new EmailAddress("pappas@gmail.com");
        driver = new Driver("john123", "john", "pappas", "696949", email, "12345123", "19", "GRE10230910290194",
                "112233", "mersedes");
        Money money = new Money(10.0, euroCurrency);
        ZipCode zip = new ZipCode("16562");
        destination = new Address("mesogeiwn", "10", "athens", zip, "greece");

        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        route1 = new Route(driver, money, LocalDateTime.of(2023, 9, 12, 10, 12), destination, 2, false);

        rate = new DriverRating(driver, route, "4.0", "4.3", "4.4");

    }

    @AfterEach
    public void tearDown() {
        email = null;
        route = null;
        route1 = null;
        driver = null;
        destination = null;
        rate = null;
    }

    @Test
    public void testGetRate() {
        driver.setRating(rate);
        assertEquals(driver.getRate(), rate);
    }

    @Test
    public void testGetUsername() {
        assertEquals(driver.getUsername(), "john123");
    }

    @Test
    public void testGetName() {
        assertEquals(driver.getName(), "john");
    }

    @Test
    public void testGetSurname() {
        assertEquals(driver.getSurname(), "pappas");
    }

    @Test
    public void testGetPhone() {
        assertEquals(driver.getPhone(), "696949");
    }

    @Test
    public void testGetEmail() {
        assertEquals(driver.getEmail(), email);
    }

    @Test
    public void testGetPassword() {
        assertEquals(driver.getPassword(), "12345123");
    }

    @Test
    public void testChangePersonalDetailsFatherMethod() {
        driver.changePersonalDetails("john322", driver.getName(), driver.getSurname(), driver.getPhone(),
                driver.getEmail(), driver.getAge());
        assertEquals(driver.getUsername(), "john322");
    }

    @Test
    public void testChangePassword() {
        driver.changePassword("123454321");
        assertEquals(driver.getPassword(), "123454321");
    }

    @Test
    public void changePersonalDetails() {
        driver.changePersonalDetails("john321", driver.getName(), driver.getSurname(), driver.getPhone(),
                driver.getEmail(), driver.getAge(), driver.getLicenseNumber(), driver.getCarType());
        assertEquals(driver.getUsername(), "john321");
    }

    @Test
    public void testGetIban() {
        assertEquals(driver.getIban(), "GRE10230910290194");
    }

    @Test
    public void testGetLicenseNumber() {
        assertEquals(driver.getLicenseNumber(), "112233");
    }

    @Test
    public void testGetCarType() {
        assertEquals(driver.getCarType(), "mersedes");
    }

    @Test
    public void testChangeIban() {
        driver.changeIban("GRE123456789");
        assertEquals(driver.getIban(), "GRE123456789");
    }

    @Test
    public void testChangeIbanToNull() {
        // Verify that a NullPointerException is thrown with the expected message
        NullPointerException exception = assertThrows(NullPointerException.class, () -> driver.changeIban(null));
        assertEquals("IBAN cannot be null", exception.getMessage());
    }

    @Test
    public void testChangeLicenseNumber() {
        driver.changeLicenseNumber("112234");
        assertEquals(driver.getLicenseNumber(), "112234");
    }

    @Test
    public void testChangeLicenseNumberToNull() {
        // Verify that a NullPointerException is thrown with the expected message
        NullPointerException exception = assertThrows(NullPointerException.class, () -> driver.changeLicenseNumber(null));
        assertEquals("License number cannot be null", exception.getMessage());
    }

    @Test
    public void testChangeCarTypeToNull() {
        // Verify that a NullPointerException is thrown with the expected message
        NullPointerException exception = assertThrows(NullPointerException.class, () -> driver.changeCarType(null));
        assertEquals("Car type cannot be null", exception.getMessage());
    }

    @Test
    public void testChangeCarType() {
        driver.changeCarType("bmw");
        assertEquals(driver.getCarType(), "bmw");
    }


    @Test
    public void testAddRoute() {
        driver.addRoute(route);
        assertTrue(driver.hasRoute(route));
        assertTrue(driver.getRoutes().contains(route));
    }

    @Test
    public void testRemoveRoute() {
        driver.addRoute(route);
        driver.removeRoute(route);
        assertFalse(driver.getRoutes().contains(route));
    }

    @Test
    public void testRemoveRouteWhenNotInList() {
        driver.getRoutes().clear();
        // Add a route to the driver's set
        driver.addRoute(route);

        // Attempt to remove a different route
        driver.removeRoute(route1);

        // Verify that the original route is no longer in the set
        assertTrue(driver.getRoutes().contains(route));
        assertFalse(driver.getRoutes().contains(route1));
    }

    @Test
    public void testRemoveRouteWhenListIsEmpty() {
        driver.getRoutes().clear();
        // Use assertThrows to check if an UnsupportedOperationException is thrown
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> driver.removeRoute(route));
        // Verify the exception message
        assertEquals("Cannot remove from an empty route_data set.", exception.getMessage());
        // Ensure that the routes set is still empty
        assertTrue(driver.getRoutes().isEmpty());
    }
}
