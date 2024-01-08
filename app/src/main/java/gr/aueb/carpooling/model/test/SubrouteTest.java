package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.Currency;

import gr.aueb.carpooling.model.AppGlobals;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;

public class SubrouteTest {
    private Subroute subroute;
    private Address destination;
    private Address destination1;
    private Address pickupPoint;
    private Address pickupPoint1;
    private LocalDateTime pickupTime;
    private LocalDateTime pickupTime1;
    private Money cost;

    private Currency euroCurrency = Currency.getInstance("EUR");

    @BeforeEach
    public void setup() {
        destination = new Address("elpidos", "10", "larisa", new ZipCode("10952"), "greece");
        destination1 = new Address("elpidos", "12", "larisa", new ZipCode("10954"), "greece");
        pickupPoint = new Address("mesogeiwn", "10", "athens", new ZipCode("16562"), "greece");
        pickupPoint1 = new Address("mesogeiwn", "16", "athens", new ZipCode("16563"), "greece");
        pickupTime = LocalDateTime.of(2023, 12, 1, 12, 30);
        pickupTime1 = LocalDateTime.of(2023, 12, 1, 11, 30);
        subroute = new Subroute(destination, pickupPoint, pickupTime);
    }

    @Test
    public void testGetDestination() {
        assertEquals(destination, subroute.getDestination());
    }

    @Test
    public void testGetPickupPoint() {
        assertEquals(pickupPoint, subroute.getPickupPoint());
    }

    @Test
    public void testGetPickupTime() {
        assertEquals(pickupTime, subroute.getPickupTime());
    }


    @Test
    public void testSetValidSubroute() {
        subroute.setDestination(destination1);
        assertEquals(destination1, subroute.getDestination());

        subroute.setPickupPoint(pickupPoint1);
        assertEquals(pickupPoint1, subroute.getPickupPoint());

        subroute.setPickupTime(pickupTime1);
        assertEquals(pickupTime1, subroute.getPickupTime());
    }

    @Test
    public void testCalculateCost() {

        // Calculate the expected distance between the addresses (you may need to adjust this)
        double expectedDistance = destination.calculateDistance( pickupPoint);

        // Call the calculateDistance method
        Money cost = subroute.calculateCost();
        subroute.setCost(cost);
        double expected_cost = expectedDistance * AppGlobals.COST_PER_KM;
        Money money_expected_cost = new Money(expected_cost,euroCurrency);

        assertEquals(subroute.getCost().getAmount(), money_expected_cost.getAmount(), 0.001);
        // Compare the expected distance with the calculated distance
        assertEquals(expected_cost, cost.getAmount(), 0.001); // Adjust the tolerance as needed
    }

    @Test
    public void testStatus(){
        subroute.setStatus(Request_status.APPROVED);
        assertEquals(Request_status.APPROVED, subroute.getStatus());
    }


}
