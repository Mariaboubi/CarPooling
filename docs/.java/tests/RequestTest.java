package carPooling;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class RequestTest {
    private Request request;
    private Passenger passenger;
    private Driver driver;
    private Address destination;
    private Address pickupPoint;
    private Time pickupTime;

    @Before
    public void setup() {
        request = new Request();
        passenger = new Passenger();
        driver = new Driver();
        destination = new Address();
        pickupPoint = new Address();
        pickupTime = new Time(0);
    }
    
   

    @Test
    public void testGetSetPassenger() {
        request.setPassenger(passenger);
        assertEquals(passenger, request.getPassenger());
    }

    @Test
    public void testGetSetDriver() {
        request.setDriver(driver);
        assertEquals(driver, request.getDriver());
    }

    @Test
    public void testGetSetDestination() {
        request.setDestination(destination);
        assertEquals(destination, request.getDestination());
    }

    @Test
    public void testGetSetPickupPoint() {
        request.setPickupPoint(pickupPoint);
        assertEquals(pickupPoint, request.getPickupPoint());
    }

    @Test
    public void testGetSetPickupTime() {
        request.setPickupTime(pickupTime);
        assertEquals(pickupTime, request.getPickupTime());
    }

    @Test
    public void testGetSetRequestStatus() {
        String status = "Pending";
        request.setRequestStatus(status);
        assertEquals(status, request.getRequestStatus());
    }
}
