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

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class DriverDAOTest {
    private DriverDAO driverDAO;
    private UserDAO userDAO;

    private Driver driver;
    private User user;

    @BeforeEach
    public void setUp() {
        driverDAO = new DriverDAOmemory();
        userDAO = new UserDAOmemory();

        user = new User("maria123", "maria", "pappa", "6900000000", new EmailAddress("pappas@gmail.com"), "12345678", "25");
        userDAO.save(user);

        driver = new Driver(user.getUsername(), user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.getAge(), "GRE10230910290194", "112233", "mersedes");
        driverDAO.save(driver);
    }

    @AfterEach
    public void tearDown() {
        driverDAO.deleteAll();
    }

    @Test
    public void testFind() {
        boolean found = driverDAO.find(driver.getUsername());
        assertTrue(found);
    }

    @Test
    public void testFindNonExistent() {
        boolean found = driverDAO.find("nonexistent");
        assertFalse(found);
    }

    @Test
    public void testFindByUsername() {
        Driver foundDriver = driverDAO.findByUsername(driver.getUsername());
        assertNotNull(foundDriver);
        assertEquals(driver, foundDriver);
    }

    @Test
    public void testFindByUsernameNonExistent() {
        Driver foundDriver = driverDAO.findByUsername("nonexistent");
        assertNull(foundDriver);
    }

    @Test
    public void testFindAll() {
        ArrayList<Driver> allDrivers = driverDAO.findAll();
        assertTrue(allDrivers.contains(driver));
    }
}
