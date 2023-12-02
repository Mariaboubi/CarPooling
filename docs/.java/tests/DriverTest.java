import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.NoSuchElementException;

public class DriverTest {
    Driver driver;
    RouteData route_data1;
    EmailAddress email = new EmailAddress("pappas@gmail.com");
    Address destination2 = new Address("mesogeiwn", "10", "athens", 16562, "greece");
    Address destination1 = new Address("mesogeiwn", "12", "athens", 16565, "greece");

    @Before
    public void setUp() {
        driver = new Driver(1,"john123", "john", "pappas", "696949",email, "12345123", 19 , 4.2,"GRE10230910290194","112233","mersedes")
       
        route_data1 = new RouteData(10, LocalDateTime.of(2023, 10, 12, 10, 12), destination2,20.0,3,this.driver);
    }

    @Test
    public void getUserId() {
        assertEquals(driver.getUserId(), 1);
    }

    @Test
    public void getUsername() {
        assertEquals(driver.getUsername(), "john123");
    }

    @Test
    public void getName() {
        assertEquals(destination.getName(), "john");
    }

    @Test
    public void getSurname() {
        assertEquals(chef.getSurname(), "pappas");
    }

    @Test
    public void getTelephone() {
        assertEquals(driver.getPhone(), "696949");
    }

    @Test
    public void getEmail() {
        assertEquals(driver.getEmail(), email);
    }

    @Test
    public void getPassword() {
        assertEquals(driver.getPassword(), "12345123");
    }

    @Test
    public void changePersonalDetailsFatherMethod() {// we decide to change only the username , so we use the getters
                                                     // for the other arguments
        driver.changePersonalDetails("john322", driver.getName(), driver.getSurname(), driver.getPhone(),
                driver.getEmail(), driver.getAge());
        assertEquals(driver.getUsername(), "john322");
    }

    @Test
    public void changePassword() {
        driver.changePassword("123454321");
        assertEquals(driver.getPassword(), "123454321");
    }

    @Test
    public void changePersonalDetails() { // we decide to change only the username, so we use the getters for the other
                                          // arguments
        driver.changePersonalDetails("john321", driver.getName(), driver.getSurname(), driver.getPhone(),
                driver.getEmail(), driver.getAge(), driver.getLicenseNumber(), driver.getCarType());
        assertEquals(driver.getUsername(), "john321");
    }

    @Test
    public void getIban() {
        assertEquals(driver.getIban(), "GRE10230910290194");
    }

    @Test
    public void getLicenseNumber() {
        assertEquals(driver.getLicenseNumber(), "112233");
    }

    public void getCarType() {
        assertEquals(driver.getCarType(), "mersedes");
    }

    @Test
    public void changeIban() {
        driver.changeIban("GRE123456789");
        assertEquals(driver.getIban(), "GRE123456789");
    }

    @Test
    public void getOrders() {
        RouteData route_data2 = new RouteData(13, LocalDateTime.of(2023, 1, 2, 9, 12), destination1, 30.0, 4,
                this.driver);
        driver.addRoute(route_data1);
        driver.addRoute(route_data2);
        assertTrue(driver.getRouteData().contains(route_data1));
        assertTrue(driver.getRouteData().contains(route_data2));
    }

    @Test
    public void addOrder() {
        driver.addRoute(route_data1);
        assertTrue(driver.getRouteData().contains(route_data1));
    }

    @Test
    public void removeOrder() {
        driver.addRoute(route_data2);
        assertTrue(driver.getRouteData().contains(route_data2));
    }

    @Test
    public void removeOrderWhenNotInList() {
        driver.addRoute(route_data1);
        RouteData route_data = new RouteData(15, LocalDateTime.of(2023, 1, 2, 11, 12), destination1, 15.0, 2,this.driver);
        assertFalse(driver.removeroute(route_data));
    }
}