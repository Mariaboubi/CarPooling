package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.dao.Initializer;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.DriverRatingDAOmemory;
import gr.aueb.carpooling.model.memoryDao.MemoryInitialized;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class DAOTest {
    private DriverDAO driverDAO;
    private PassengerDAO passengerDAO;
    private DriverRatingDAO driverRatingDAO;

    private PassengerRatingDao passengerRatingDao;
    private RouteDAO routeDAO;

    private SubrouteDAO subrouteDAO;

    private UserDAO userDAO;

    private User user1;

    private Driver driver1;

    private static final int INITIAL_DRIVER_COUNT =2;
    private static final int INITIAL_PASSENGER_COUNT =2;
    private static final int INITIAL_USER_COUNT =5;
    private static final int INITIAL_ROUTE_COUNT =3;
    private static final int INITIAL_SUBROUTE_COUNT =2;
    private static final int INITIAL_DRIVER_RAITING_COUNT =1;
    private static final int INITIAL_PASSENGER_RATING_COUNT =0;


    @BeforeEach
    public void setUp(){
        Initializer dataHelper = new MemoryInitialized();
        dataHelper.prepareData();

        driverDAO= new DriverDAOmemory();
        passengerDAO= new PassengerDAOmemory();
        passengerRatingDao=new PassengerRatingDAOmemory();
        driverRatingDAO= new DriverRatingDAOmemory();
        routeDAO=new RouteDAOmemory();
        subrouteDAO=new SubrouteDAOmemory();
        userDAO=new UserDAOmemory();

        EmailAddress email1 = new EmailAddress("pappas@gmail.com");
        user1=new User("maria123","maria","pappa","6900000000",email1,"12345678","25");
        driver1= new Driver(user1.getUsername(),user1.getName(),user1.getSurname(),user1.getPhone(), user1.getEmail(),user1.getPassword(), user1.getAge(), "GRE10230910290194", "112233", "mersedes");
    }
    @AfterEach
    public void tearDown(){
        MemoryInitialized dataHelper = new MemoryInitialized();
        dataHelper.eraseAll();
    }
    @Test
    public void checkSize(){
        assertEquals(INITIAL_DRIVER_COUNT,driverDAO.findAll().size());
        assertEquals(INITIAL_PASSENGER_COUNT,passengerDAO.findAll().size());
        assertEquals(INITIAL_DRIVER_RAITING_COUNT,driverRatingDAO.findAll().size());
        assertEquals(INITIAL_PASSENGER_RATING_COUNT,passengerRatingDao.findAll().size());
        assertEquals(INITIAL_ROUTE_COUNT,routeDAO.findAll().size());
        assertEquals(INITIAL_SUBROUTE_COUNT,subrouteDAO.findAll().size());
        assertEquals(INITIAL_USER_COUNT,userDAO.size());
    }

    @Test
    public void TestUserDao(){
        assertEquals(user1,userDAO.findByUsername("maria123"));
//        assertEquals(user1,userDAO.findByUsernameAndPassword("maria123","12345678"));

    }

    @Test
    public void TestDriverDao(){
        assertTrue(driverDAO.find("maria123"));
        assertEquals(driver1,driverDAO.findByUsername("maria123"));
        int id= driver1.getDriverId();
        assertTrue(driverDAO.findDriver(id));
        assertEquals(driver1,driverDAO.find(id));


    }




}
