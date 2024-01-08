package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.memoryDao.DriverRatingDAOmemory;

public class DriverRatingDAOTest {
    private DriverRatingDAO driverRatingDAO;

    private Driver driver1;
    private Driver driver2;
    private Route route1;
    private Route route2;
    private DriverRating driverRating1;
    private DriverRating driverRating2;

    @BeforeEach
    public void setUp() {
        driverRatingDAO = new DriverRatingDAOmemory();

        driver1 = new Driver("maria123", "maria", "maria", "1234567890", new EmailAddress("maria@gmail.com"), "12345678", "20", "1234567890", "License1", "bmw");
        driver2 = new Driver("eleni123", "eleni", "eleni", "6980000000",  new EmailAddress("eleni@gmail.com"), "00000000", "20", "9876543210", "License2", "mercendes");
        route1 = new Route(driver1, null, null, null, 0, false);
        route2 = new Route(driver2, null, null, null, 0, false);
        driverRating1 = new DriverRating(driver1, route1, "4.0", "4.5", "3.5");
        driverRating2 = new DriverRating(driver2, route2, "3.5", "3.0", "4.0");

        driverRatingDAO.save(driverRating1);
        driverRatingDAO.save(driverRating2);
    }

    @AfterEach
    public void tearDown() {
        driverRatingDAO.deleteAll();
    }

    @Test
    public void testFindAll() {
        // Check if both driver ratings are in the DAO
        assertTrue(driverRatingDAO.findAll().contains(driverRating1));
        assertTrue(driverRatingDAO.findAll().contains(driverRating2));
    }

    @Test
    public void testFindAllEmpty() {
        // Clear all driver ratings from the DAO
        driverRatingDAO.deleteAll();

        // Check if the DAO is empty
        assertTrue(driverRatingDAO.findAll().isEmpty());
    }
}