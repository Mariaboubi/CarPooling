package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Currency;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class RouteDAOTest {
    private RouteDAO routeDAO;
    private SubrouteDAO subrouteDAO;
    private UserDAO userDAO;
    private DriverDAO driverDAO;
    private PassengerDAO passengerDAO;

    private User user;
    private Driver driver;
    private Passenger passenger;
    private Route route;
    private Subroute subroute1;
    private Subroute subroute2;

    @BeforeEach
    public void setUp() {
        routeDAO = new RouteDAOmemory();
        subrouteDAO = new SubrouteDAOmemory();
        userDAO = new UserDAOmemory();
        driverDAO = new DriverDAOmemory();
        passengerDAO = new PassengerDAOmemory();

        user = new User("maria123", "maria", "pappa", "6900000000", new EmailAddress("pappas@gmail.com"), "12345678", "25");
        userDAO.save(user);

        driver = new Driver(user.getUsername(), user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.getAge(), "GRE10230910290194", "112233", "mersedes");
        driverDAO.save(driver);

        User user2 = new User("markos_andre", "markos", "andreopoulos", "6972169794", new EmailAddress("markos@gmail.com"), "11111111", "27");
        userDAO.save(user2);

        passenger = new Passenger(user2.getUsername(), user2.getName(), user2.getSurname(), user2.getPhone(), user2.getEmail(), user2.getPassword(), user2.getAge(), "10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger);

        subroute1 = new Subroute(new Address("Tinou", "12", "Xalkida", new ZipCode("12456"), "greece"), new Address("Euelpidon", "9", "Athens", new ZipCode("15461"), "greece"), LocalDateTime.of(2024, 1, 28, 16, 30));
        subrouteDAO.save(subroute1);

        subroute2 = new Subroute(new Address("Ermou", "12", "Xalkida", new ZipCode("11455"), "greece"), new Address("Kifisias", "109", "Athens", new ZipCode("12568"), "greece"), LocalDateTime.of(2024, 1, 28, 16, 30));
        subrouteDAO.save(subroute2);

        route = new Route(
                driver,
                new Money(100.0, Currency.getInstance("EUR")),
                LocalDateTime.of(2024, 1, 6, 10, 12),
                new Address("athinas", "2", "athens", new ZipCode("12562"), "greece"),
                3,
                false);
        route.addPassengerRoute(passenger, subroute1);
        routeDAO.save(route);

        passenger.addRoute(route);
    }

    @AfterEach
    public void tearDown() {
        routeDAO.deleteAll();
    }

    @Test
    public void testFindById() {
        Route foundRoute = routeDAO.find(route.getId());
        assertNotNull(foundRoute);
        assertEquals(route, foundRoute);
    }

    @Test
    public void testFindByIdNonExistent() {
        Route foundRoute = routeDAO.find(999);
        assertNull(foundRoute);
    }

    @Test
    public void testFindAll() {
        ArrayList<Route> allRoutes = routeDAO.findAll();
        assertTrue(allRoutes.contains(route));
    }

    @Test
    public void testFindByDriver() {
        ArrayList<Route> routesByDriver = routeDAO.findByDriver(driver);
        assertTrue(routesByDriver.contains(route));
    }

    @Test
    public void testFindByDriverIsCompleted() {
        route.Completed();
        ArrayList<Route> completedRoutesByDriver = routeDAO.findByDriverIsCompleted(driver);
        assertTrue(completedRoutesByDriver.contains(route));
    }

    @Test
    public void testFindSubroutesByPassenger() {
        ArrayList<Subroute> subroutesByPassenger = routeDAO.findSubroutesByPassenger(passenger);
        assertTrue(subroutesByPassenger.contains(subroute1));
    }

    @Test
    public void testFindRouteByPassAndSub() {
        Route foundRoute = routeDAO.findRouteByPassAndSub(passenger, subroute1);

        assertNotNull(foundRoute);
        assertEquals(route, foundRoute);
    }

    @Test
    public void testFindPassengerBySubroute() {
        Passenger foundPassenger = routeDAO.findPassengerBySubroute(subroute1);
        assertNotNull(foundPassenger);
        assertEquals(passenger, foundPassenger);
    }

    @Test
    public void testFindRouteBySubroute() {
        Route foundRoute = routeDAO.findRouteBySubroute(subroute1);
        assertNotNull(foundRoute);
        assertEquals(route, foundRoute);
    }

    @Test
    public void testFindRouteByPassAndSubNonExistent() {
        Route foundRoute = routeDAO.findRouteByPassAndSub(passenger, subroute2);
        assertNull(foundRoute);
    }
}