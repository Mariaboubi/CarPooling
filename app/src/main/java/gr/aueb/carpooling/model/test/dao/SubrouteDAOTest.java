package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class SubrouteDAOTest {

    private static SubrouteDAO subrouteDAO;
    private static RouteDAO routeDAO;
    private static UserDAO userDAO;

    private static PassengerDAO passengerDAO;
    private static DriverDAO driverDAO;

    private static User user;
    private static Driver driver;
    private static Passenger passenger;
    private static Route route;
    private static Subroute subroute1;
    private static Subroute subroute2;

    @BeforeEach
    public void setUp() {
        /* Initialize DAOs */
        driverDAO = new DriverDAOmemory();
        passengerDAO = new PassengerDAOmemory();
        routeDAO = new RouteDAOmemory();
        subrouteDAO = new SubrouteDAOmemory();
        userDAO = new UserDAOmemory();

        /* Test users, one driver, one passenger */
        user = new User(
                "maria123",
                "maria",
                "pappa",
                "6900000000",
                new EmailAddress("pappas@gmail.com"),
                "12345678",
                "25"
        );
        userDAO.save(user);

        driver = new Driver(user.getUsername(), user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.getAge(), "GRE10230910290194", "112233", "mersedes");
        driverDAO.save(driver);

        User user2 = new User(
                "markos_andre",
                "markos",
                "andreopoulos",
                "6972169794",
                new EmailAddress("markos@gmail.com"),
                "11111111",
                "27");
        userDAO.save(user2);

        passenger = new Passenger(user2.getUsername(), user2.getName(), user2.getSurname(), user2.getPhone(), user2.getEmail(), user2.getPassword(), user2.getAge(), "10230910290333", "Markos kapelas", "481");
        passenger.addRoute(route);
        passengerDAO.save(passenger);

        /* Two test subroutes */
        subroute1 = new Subroute(
                new Address("Tinou", "12", "Xalkida", new ZipCode("12456"), "greece"),
                new Address("Euelpidon", "9", "Athens", new ZipCode("15461"), "greece"),
                LocalDateTime.of(2024, 1, 28, 16, 30)
        );
        subrouteDAO.save(subroute1);

        subroute2 = new Subroute(
                new Address("Ermou", "12", "Xalkida", new ZipCode("11455"), "greece"),
                new Address("Kifisias", "109", "Athens", new ZipCode("12568"), "greece"),
                LocalDateTime.of(2024, 1, 28, 16, 30)
        );
        subrouteDAO.save(subroute2);

        /* Test routes */
        route = new Route(
                driver,
                new Money(100.0, Currency.getInstance("EUR")),
                LocalDateTime.of(2024, 1, 6, 10, 12),
                new Address("athinas", "2", "athens", new ZipCode("12562"), "greece"),
                3,
                false
        );
        routeDAO.save(route);
    }

    @AfterEach
    public void tearDown() {
        subrouteDAO.deleteAll();
    }

    @Test
    public void testFindById() {
        // Call the method being tested with the ID of subroute1
        Subroute foundSubroute = subrouteDAO.findById(subroute1.getId());

        assertEquals(subroute1, foundSubroute);
    }

    @Test
    public void testFindByIdNonExistent() {
        // Call the method being tested with an ID that doesn't exist
        Subroute foundSubroute = subrouteDAO.findById(999);

        // Verify that null is returned for a non-existent ID
        assertNull(foundSubroute);
    }

    @Test
    public void testFindAll() {
        // Call the method being tested
        ArrayList<Subroute> allSubroutes = subrouteDAO.findAll();

        // Verify that all saved Subroutes are present in the result
        assertTrue(allSubroutes.contains(subroute1));
        assertTrue(allSubroutes.contains(subroute2));
    }

    @Test
    public void testFindAllEmpty() {
        // Clear all Subroutes from the DAO
        subrouteDAO.deleteAll();

        // Call the method being tested on an empty DAO
        ArrayList<Subroute> allSubroutes = subrouteDAO.findAll();

        // Verify that the result is an empty list
        assertTrue(allSubroutes.isEmpty());
    }
}
