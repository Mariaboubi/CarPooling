package carPooling;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RouteTest {
    private Route route;
    private Driver driver;
    private List<Passenger> passengers;
    private RouteData routeData;

    @Before
    public void setup() {
        route = new Route();
        driver = new Driver();
        passengers = new ArrayList<>();
        routeData = new RouteData();
    }

    @Test
    public void testGetSetDriver() {
        route.setDriver(driver);
        assertEquals(driver, route.getDriver());
    }

    @Test
    public void testGetSetPassengers() {
        passengers.add(new Passenger());
        route.setPassengers(passengers);
        assertEquals(passengers, route.getPassengers());
    }

    @Test
    public void testGetSetRouteData() {
        route.setRouteData(routeData);
        assertEquals(routeData, route.getRouteData());
    }

    @Test
    public void testCalculateCost() {
        Money expectedCost = new Money(10); 
        Money calculatedCost = route.calculateCost();
        assertNotNull(calculatedCost);
        assertEquals(expectedCost, calculatedCost);
    }
}

