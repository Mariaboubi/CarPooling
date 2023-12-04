package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Currency;

import gr.aueb.carpooling.model.AppGlobals;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class RouteTest {
    private Route route;
    private Driver driver;
    private Address destination;
    private Money money;
    private Driver driver1;
    private Money money5;
    private Passenger passenger;
    private Address destination2;
    private Subroute subroute;
    private Address pickupPoint;
    private LocalDateTime pickupTime;
    private final Currency euroCurrency = Currency.getInstance("EUR");

    @BeforeEach
    public void setup() {
        EmailAddress email = new EmailAddress("pappas@gmail.com");
        EmailAddress email2 =  new EmailAddress("pappadia@gmail.com");
        driver = new Driver("john123", "john", "pappas", "696949", email, "12345123", 19, "GRE10230910290194",
                "112233", "mersedes");
        driver1 = new Driver("maria123", "maria", "pappas", "696948", email, "12345123", 19, "GRE10230910290194",
                "112233", "mersedes");
        money = new Money(10.0, euroCurrency);
        destination = new Address("mesogeiwn", "10", "athens", new ZipCode("16562",0.0,0.0), "greece");
        route = new Route(driver, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        money5 = new Money(5.0, euroCurrency);

        pickupPoint = new Address("mesogeiwn", "16", "athens", new ZipCode("16563",0.0,0.0), "greece");
        pickupTime = LocalDateTime.of(2023, 12, 1, 12, 30);
        subroute = new Subroute(destination, pickupPoint, pickupTime);
        passenger = new Passenger("eleni3", "eleni", "pappa", "6969497297", email2, "12345183", 23, "14222",
                "eleni", "352");
    }

    @Test
    public void testGetDriver() {
        assertEquals(driver, route.getDriver());
    }

    @Test
    public void testGetCompleted() {
        assertFalse(route.isCompleted());
    }

    @Test
    public void testMaxPassenger() {
        assertEquals(3, route.getMaxPassengers());
    }

    @Test
    public void testEstimatedCost() {
        assertEquals(money, route.getEstimatedCost());
    }

    @Test
    public void testDestination() {
        assertEquals(destination, route.getDestination());
    }

    @Test
    public void testDate() {
        assertEquals(LocalDateTime.of(2023, 10, 12, 10, 12), route.getDate());
    }

    @Test
    public void testSetValidRoute() {
        // Set a new driver and check if it's updated
        route.setDriver(driver1);
        assertEquals(driver1, route.getDriver());

        // Set a new destination and check if it's updated
        route.setDestination(destination2);
        assertEquals(destination2, route.getDestination());

        // Mark the route as completed and check if it's marked as completed
        route.Completed();
        assertTrue(route.isCompleted());

        // Change the maximum number of passengers and check if it's updated
        route.setMaxPassengers(2);
        assertEquals(2, route.getMaxPassengers(), 0.0f);

        // Change the date and time and check if it's updated
        LocalDateTime newDateTime = LocalDateTime.of(2023, 12, 1, 11, 30);
        route.setDate(newDateTime);
        assertEquals(newDateTime, route.getDate());

        // Change the estimated cost and check if it's updated
        route.setEstimatedCost(money5);
        assertEquals(money5, route.getEstimatedCost());

    }

    @Test
    public void testAddPassenger() {
        route.addPassenger(passenger, subroute);
        assertNotNull(route.getSubRouteByPassenger(passenger));
        assertNotNull(route.getPassengerRoutes());
    }

    @Test
    public void testGetPassenger() {
        route.addPassenger(passenger, subroute);
        assertTrue(route.getPassengers().contains(passenger));
    }

    @Test
    public void testRemovePassenger() {
        route.addPassenger(passenger, subroute);
        route.removePassenger(passenger);
        assertFalse(route.getPassengers().contains(passenger));
    }

    @Test
    public void testCalculateCost() {
        route.addPassenger(passenger,subroute);
        // Calculate the expected distance between the addresses (you may need to adjust this)
        double expectedDistance = destination.calculateDistance( pickupPoint);

        // Call the calculateDistance method
        Money cost = subroute.calculateCost();
        subroute.setCost(cost);
        double expected_cost = expectedDistance * AppGlobals.COST_PER_KM;
        Money money_expected_cost = new Money(expected_cost,euroCurrency);
        route.calculateTotalCost();

        assertEquals(money_expected_cost.getAmount(),route.getPassengerCost(passenger).getAmount());
        assertEquals(route.getTotalCost().getAmount(), money_expected_cost.getAmount(), 0.001);
    }
}
