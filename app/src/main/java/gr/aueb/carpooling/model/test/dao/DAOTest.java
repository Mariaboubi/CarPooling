package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Currency;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
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

    private Currency euroCurrency;

    private Money money;

    private Address destination;

    private Route route;

    private DriverRating driver_rating ;

    private PassengerRating passenger_rating ;

    private Passenger passenger1;

    private Subroute subroute3;

    private static final int INITIAL_DRIVER_COUNT =2;
    private static final int INITIAL_PASSENGER_COUNT =2;
    private static final int INITIAL_USER_COUNT =5;
    private static final int INITIAL_ROUTE_COUNT =4;
    private static final int INITIAL_SUBROUTE_COUNT =2;
    private static final int INITIAL_DRIVER_RAITING_COUNT =1;
    private static final int INITIAL_PASSENGER_RATING_COUNT =1;




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

        euroCurrency = Currency.getInstance("EUR");
        money = new Money(100.0, euroCurrency);
        destination = new Address("mesogeiwn", "10", "athens", new ZipCode("16562"), "greece");
        route = new Route(driver1, money, LocalDateTime.of(2024, 1, 6, 10, 12), destination, 3, false);

        driver_rating = new DriverRating(driver1,route,"4.5","5.0","3.5");

        EmailAddress email2 = new EmailAddress("markos@gmail.com");
        User user2= new User("markos_andre","markos","andreopoulos","6972169794",email2,"11111111","27");
        passenger1= new Passenger(user2.getUsername(),user2.getName(),user2.getSurname(),user2.getPhone(), user2.getEmail(),user2.getPassword(), user2.getAge(),"10230910290333", "Markos kapelas", "481");
        passenger_rating= new PassengerRating(passenger1,route,"4.0","4.0","4.0");

        Address sub_destination3 = new Address("Xaras", "12", "Xalkida", new ZipCode("11456"), "greece");
        Address sub_pickuppoint3 = new Address("Euelpidon", "9", "Athens", new ZipCode("15561"), "greece");
        subroute3 = new Subroute(sub_destination3,sub_pickuppoint3,LocalDateTime.of(2024, 1, 28, 16, 30));
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
        assertEquals(INITIAL_USER_COUNT,userDAO.findAll().size());
    }

    @Test
    public void TestUserDao(){
        assertEquals(user1.getUsername(),userDAO.findByUsername("maria123").getUsername());
        assertNull(userDAO.findByUsername("maria"));
        assertNull(userDAO.findByUsernameAndPassword("maria","1111"));
        assertEquals(user1.getUsername(),userDAO.findByUsernameAndPassword("maria123","12345678").getUsername());

    }

    @Test
    public void TestDriverDao(){
        assertTrue(driverDAO.find("maria123"));
        assertFalse(driverDAO.find("maria12"));
        assertEquals(driver1.getUsername(),driverDAO.findByUsername("maria123").getUsername());
        assertNull(driverDAO.findByUsername("maria12"));

    }

    @Test
    public void TestPassengerDao(){
        assertTrue(passengerDAO.find("markos_andre"));
        assertFalse(passengerDAO.find("maria12"));
        assertEquals(passenger1.getUsername(),passengerDAO.findByUsername("markos_andre").getUsername());
        assertNull(passengerDAO.findByUsername("maria12"));
        ArrayList<Passenger> pas_list=new ArrayList<>();
        pas_list.add(passenger1);
//        assertEquals(pas_list.size(),passengerDAO.findAllByRoute(route).size());


    }

    @Test
    public void TestPassengerRatingDao(){
        passengerRatingDao.save(passenger_rating);
        ArrayList<PassengerRating> rate_list=new ArrayList<>();
        rate_list.add(passenger_rating);
        assertEquals(rate_list.size(),passengerRatingDao.findAllByRoute(route).size());

    }


    @Test
    public void TestSubrouteDao(){
        subrouteDAO.delete(subroute3);
        //assertEquals(2,subrouteDAO.findAll().size());
//        int id= subroute3.getId();
//        assertEquals(subroute3.getDestination().toString(),subrouteDAO.findById(id).getDestination().toString());

    }

    @Test
    public void TestRoutes(){
        route.addPassengerRoute(passenger1,subroute3);
        assertEquals(route.getDriver().getUsername(),routeDAO.findRouteByPassAndSub(passenger1,subroute3).getDriver().getUsername());
    }




}
