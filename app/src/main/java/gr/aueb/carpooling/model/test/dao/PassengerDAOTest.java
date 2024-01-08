package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class PassengerDAOTest {
    private PassengerDAO passengerDAO;
    private UserDAO userDAO;

    private Passenger passenger;
    private User user;

    @BeforeEach
    public void setUp() {
        passengerDAO = new PassengerDAOmemory();
        userDAO = new UserDAOmemory();

        user = new User("markos_andre", "markos", "andreopoulos", "6972169794", new EmailAddress("markos@gmail.com"), "11111111", "27");
        userDAO.save(user);

        passenger = new Passenger(user.getUsername(), user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.getAge(), "10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger);
    }

    @AfterEach
    public void tearDown() {
        passengerDAO.deleteAll();
    }

    @Test
    public void testFind() {
        boolean found = passengerDAO.find(passenger.getUsername());
        assertTrue(found);
    }

    @Test
    public void testFindNonExistent() {
        boolean found = passengerDAO.find("nonexistent");
        assertFalse(found);
    }

    @Test
    public void testFindByUsername() {
        Passenger foundPassenger = passengerDAO.findByUsername(passenger.getUsername());
        assertNotNull(foundPassenger);
        assertEquals(passenger, foundPassenger);
    }

    @Test
    public void testFindByUsernameNonExistent() {
        Passenger foundPassenger = passengerDAO.findByUsername("nonexistent");
        assertNull(foundPassenger);
    }

    @Test
    public void testFindAllByRoute() {
        Route route = new Route(null, null, null, null, 0, false);
        passenger.addRoute(route);
        ArrayList<Passenger> passengersByRoute = passengerDAO.findAllByRoute(route);
        assertTrue(passengersByRoute.contains(passenger));
    }

    @Test
    public void testFindAll() {
        ArrayList<Passenger> allPassengers = passengerDAO.findAll();
        assertTrue(allPassengers.contains(passenger));
    }
}
