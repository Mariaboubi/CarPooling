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
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class PassengerTest {
    private Passenger passenger;
    private Driver driver;
    private Route route;
    private Route route1;

    private EmailAddress email1;
    private EmailAddress email2;
    private PassengerRating rate;
    private final Currency euroCurrency = Currency.getInstance("EUR");
    private Money money;

    @BeforeEach
    public void setUp() {
        email1 = new EmailAddress("pappas@gmail.com");
        email2 = new EmailAddress("pappassmar@gmail.com");
        passenger = new Passenger("john123", "john", "pappas", "6969497299", email1, "12345123", "19", "12222",
                "john", "322");
        driver = new Driver("maria123", "maria", "pappas", "69694559", email2, "12345124", "19", "GRE10230910290194",
                "112233", "mersedes");
        money = new Money(0.0, euroCurrency);
        Address destination = new Address("Veikou", "37", "Athens", new ZipCode("11146",0.0,0.0), "Greece");
        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        route1 = new Route(driver, money, LocalDateTime.of(2023, 9, 12, 10, 12), destination, 2, false);

        rate = new PassengerRating(passenger, route, 3.0f, 4.0f, 5.0f);
    }

    @AfterEach
    public void tearDown() {
        passenger.resetBalance();
        passenger = null;
        driver = null;
        route = null;
        rate = null;
        money = null;
    }

    @Test
    public void testGetRate() {
        passenger.setRating(rate);
        assertEquals(passenger.getRate(), rate);
    }

    @Test
    public void testGetUsername() {
        assertEquals(passenger.getUsername(), "john123");
    }

    @Test
    public void testGetName() {
        assertEquals(passenger.getName(), "john");
    }

    @Test
    public void testGetSurname() {
        assertEquals(passenger.getSurname(), "pappas");
    }

    @Test
    public void testGetPhone() {
        assertEquals(passenger.getPhone(), "6969497299");
    }

    @Test
    public void testGetEmail() {
        assertEquals(passenger.getEmail(), email1);
    }

    @Test
    public void testGetPassword() {
        assertEquals(passenger.getPassword(), "12345123");
    }

    @Test
    public void testGetAge() {
        assertEquals(passenger.getAge(), 19);
    }

    @Test
    public void testChangePersonalDetails() {// we decide to change only the username
        passenger.changePersonalDetails("john322", "john", "pappas", "696949", email2, "19");
        assertEquals(passenger.getUsername(), "john322");
    }

    @Test
    public void testChangePassword() {
        passenger.changePassword("123454321");
        assertEquals(passenger.getPassword(), "123454321");
    }

    @Test
    public void testChangeBankDetails() { // we decide to change the cardNumber and keep everything else the same
        passenger.changeBankDetails("12345", "john", "322"); // might need to call GETTERS to show that these stay the
        // same
        assertEquals(passenger.getCardNumber(), "12345");
    }

    @Test
    public void testGetCardNumber() {
        assertEquals(passenger.getCardNumber(), "12222");
    }

    @Test
    public void testGetCVV() {
        assertEquals(passenger.getCVV(), "322");
    }

    @Test
    public void testGetCardHolderName() {
        assertEquals(passenger.getCardHolderName(), "john");
    }

    @Test
    public void testGetBalance() {
        assertEquals(passenger.getBalance(), new Money(0.0, euroCurrency));
    }

    @Test
    public void testTopUp() {
        passenger.topUp(new Money(10.0, euroCurrency));
        assertEquals(passenger.getBalance(), new Money(10.0, euroCurrency));
    }

    @Test
    public void testTransaction() {
        passenger.topUp(new Money(10.0, euroCurrency));
        passenger.transaction(new Money(5.0, euroCurrency));
        assertEquals(passenger.getBalance(), new Money(5.0, euroCurrency));
    }


    @Test
    public void testInvalidTopUpAmount() {
        assertThrows(IllegalStateException.class, () -> passenger.topUp(new Money(-1.0, euroCurrency)));
    }

    @Test
    public void testResetBalance() {
        passenger.topUp(new Money(10.0, euroCurrency));
        passenger.resetBalance();
        assertEquals(passenger.getBalance(), money);
    }

    @Test
    public void testAddRoute() {
        assertTrue(passenger.addRoute(route));
        assertTrue(passenger.hasRoute(route));
        assertTrue(passenger.getRoutes().contains(route));
    }

    @Test
    public void testRemoveRoute() {
        passenger.addRoute(route);
        assertTrue(passenger.removeRoute(route));
        assertFalse(passenger.getRoutes().contains(route));
    }

    @Test
    void testRemoveRouteWhenNotInList() {
        passenger.getRoutes().clear();
        // Add a route to the driver's set
        passenger.addRoute(route);

        // Attempt to remove a different route
        assertFalse(passenger.removeRoute(route1));

        // Verify that the original route is still in the set
        assertTrue(passenger.getRoutes().contains(route));
        assertFalse(passenger.getRoutes().contains(route1));
    }

    @Test
    public void testRemoveRouteWhenListIsEmpty() {
        passenger.getRoutes().clear();
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> passenger.removeRoute(route));
        assertEquals("Cannot remove from an empty route_data set.", exception.getMessage());
    }
    @Test
    public void testPaymentSuccess() {
        passenger.topUp(new Money(10.0, euroCurrency));
        passenger.payment(new Money(5.0, euroCurrency));
        assertEquals(passenger.getBalance(), new Money(5.0, euroCurrency));

    }
    @Test
    public void testPaymentUnsuccess() {
        passenger.topUp(new Money(5.0, euroCurrency));
        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class,
                () -> passenger.payment(new Money(10.0, euroCurrency)));
        assertEquals("The transaction was unsuccessful. Put more money in the card", exception.getMessage());
    }

}