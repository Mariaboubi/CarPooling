package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;

public class PassengerRatingDAOTest {
    private PassengerRatingDao passengerRatingDao;
    private Passenger passenger1;
    private Passenger passenger2;
    private Route route1;
    private Route route2;
    private PassengerRating rating1;
    private PassengerRating rating2;

    @BeforeEach
    public void setUp() {
        passengerRatingDao = new PassengerRatingDAOmemory();

        passenger1 = new Passenger("maria123", "maria", "maria", "1234567890", new EmailAddress("maria@gmail.com"), "12345678", "20", "1234567890", "License1", "Car1");
        passenger2 = new Passenger("eleni123", "eleni", "eleni", "6980000000", new EmailAddress("eleni@gmail.com"), "00000000", "20", "9876543210", "License2", "Car2");

        route1 = new Route(null, null, null, null, 1, false);
        route2 = new Route(null, null, null, null, 1, false);

        rating1 = new PassengerRating(passenger1, route1, "4.0", "4.5", "3.5");
        rating2 = new PassengerRating(passenger2, route2, "3.5", "3.0", "4.0");

        passengerRatingDao.save(rating1);
        passengerRatingDao.save(rating2);
    }

    @AfterEach
    public void tearDown() {
        passengerRatingDao.deleteAll();
    }

    @Test
    public void testFindAll() {
        // Check if both passenger ratings are in the DAO
        assertTrue(passengerRatingDao.findAll().contains(rating1));
        assertTrue(passengerRatingDao.findAll().contains(rating2));

    }

    @Test
    public void testFindAllByRoute() {
        // Check if findAllByRoute returns ratings for route1
        assertTrue(passengerRatingDao.findAllByRoute(route1).contains(rating1));
        // Check if findAllByRoute returns ratings for route2
        assertTrue(passengerRatingDao.findAllByRoute(route2).contains(rating2));
    }

    @Test
    public void testFindAllEmpty() {
        // Clear all passenger ratings from the DAO
        passengerRatingDao.deleteAll();

        // Check if the DAO is empty
        assertTrue(passengerRatingDao.findAll().isEmpty());
    }
}
