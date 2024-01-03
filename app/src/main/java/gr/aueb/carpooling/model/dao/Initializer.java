package gr.aueb.carpooling.model.dao;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.contact.EmailAddress;
import org.threeten.bp.LocalDateTime;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import java.util.Currency;

public abstract class Initializer {
    public void eraseAll() {
        UserDAO userDAO = getUserDAO();
        userDAO.deleteAll();
        DriverDAO driverDAO = getDriverDAO();
        driverDAO.deleteAll();
        PassengerDAO passengerDAO = getPassengerDAO();
        passengerDAO.deleteAll();

        RouteDAO routeDAO = getRouteDAO();
        routeDAO.deleteAll();
    }


    public void prepareData() {
        eraseAll();
        //// NEW USERS/////////////////////////////////////////////////////////////////
        UserDAO userDAO = getUserDAO();
        EmailAddress email1 = new EmailAddress("pappas@gmail.com");
        EmailAddress email2 = new EmailAddress("markos@gmail.com");
        EmailAddress email3 = new EmailAddress("kostas_03@gmail.com");

        User user1= new User("maria123","maria","pappa","6900000000",email1,"1234","25");
        User user2= new User("markos_andre","markos","andreopoulos","6972169794",email2,"1111","27");
        User user3= new User("kostas3","kostas","Papadopoulos","6972169794",email3,"2003","20");
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);

        //// NEW DRIVERS////////////////////////////////////////////////////////////////
        DriverDAO driverDAO= getDriverDAO();
        Driver driver1= new Driver(user1.getUsername(),user1.getName(),user1.getSurname(),user1.getPhone(), user1.getEmail(),user1.getPassword(), user1.getAge(), "GRE10230910290194", "112233", "mersedes");
        Driver driver2= new Driver(user2.getUsername(),user2.getName(),user2.getSurname(),user2.getPhone(), user2.getEmail(),user2.getPassword(), user2.getAge(),"GRE10230910290333", "118899", "BMW");
        driverDAO.save(driver1);
        driverDAO.save(driver2);
        // NEW PASSENGERS////////////////////////////////////////////////////////////////
        PassengerDAO passengerDAO = getPassengerDAO();
//        Passenger passenger1= new Passenger("maria123","maria","pappa","6900000000",email1,"1234","25","10230910290194", "Maria Volt", "365");
        Passenger passenger2= new Passenger(user2.getUsername(),user2.getName(),user2.getSurname(),user2.getPhone(), user2.getEmail(),user2.getPassword(), user2.getAge(),"10230910290333", "Markos kapelas", "481");
//        passengerDAO.save(passenger1);
        passengerDAO.save(passenger2);

        // NEW ROUTES //////////////////////////////////////////////////////////////////////
        RouteDAO routeDAO= getRouteDAO();
        final Currency euroCurrency = Currency.getInstance("EUR");
        Money money = new Money(10.0, euroCurrency);
        Address destination = new Address("mesogeiwn", "10", "athens", new ZipCode("16562",0.0,0.0), "greece");
        Route route1 = new Route(driver1, money, LocalDateTime.of(2023, 10, 12, 10, 12), destination, 3, false);
        Money money5 = new Money(5.0, euroCurrency);
        Address destination1 = new Address("mesogeiwn", "157", "athens", new ZipCode("16571",0.0,0.0), "greece");
        Route route2 = new Route(driver1, money5, LocalDateTime.of(2023, 10, 28, 16, 30), destination1, 2, false);
        Money money20 = new Money(20.0, euroCurrency);
        Address destination2 = new Address("tsimiski", "50", "tessaloniki", new ZipCode("54623",0.0,0.0), "greece");
        Route route3 = new Route(driver2, money20, LocalDateTime.of(2023, 1, 28, 16, 30), destination2, 4, false);
        routeDAO.save(route1);
        routeDAO.save(route2);
        routeDAO.save(route3);

    }
    public abstract UserDAO getUserDAO();

    public abstract DriverDAO getDriverDAO();

    public abstract PassengerDAO getPassengerDAO();

    public abstract RouteDAO getRouteDAO();
}