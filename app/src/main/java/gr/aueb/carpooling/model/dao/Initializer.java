package gr.aueb.carpooling.model.dao;

import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Subroute;
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

        PassengerRatingDao passengerRatingDao = getPassengerRatingDAO();
        passengerRatingDao.deleteAll();

        DriverRatingDAO driverRatingDAO = getDriverRatingDAO();
        driverRatingDAO.deleteAll();

        RouteDAO routeDAO = getRouteDAO();
        routeDAO.deleteAll();

        SubrouteDAO subrouteDAO = getSubrouteDAO();
        subrouteDAO.deleteAll();
    }


    public void prepareData() {
        eraseAll();
        //// NEW USERS/////////////////////////////////////////////////////////////////
        UserDAO userDAO = getUserDAO();
        EmailAddress email1 = new EmailAddress("pappas@gmail.com");
        EmailAddress email2 = new EmailAddress("markos@gmail.com");
        EmailAddress email3 = new EmailAddress("kostas_03@gmail.com");
        EmailAddress email4 = new EmailAddress("eleni_zanou@gmail.com");

        User user1= new User("maria123","maria","pappa","6900000000",email1,"1234","25");
        User user2= new User("markos_andre","markos","andreopoulos","6972169794",email2,"1111","27");
        User user3= new User("kostas3","kostas","Papadopoulos","6972169794",email3,"2003","21");
        User user4= new User("eleniz","eleni","Zanou","6977292186",email4,"2003","20");
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        userDAO.save(user4);

        //// NEW DRIVERS////////////////////////////////////////////////////////////////
        DriverDAO driverDAO= getDriverDAO();
        // Driver1=Maria123
        Driver driver1= new Driver(user1.getUsername(),user1.getName(),user1.getSurname(),user1.getPhone(), user1.getEmail(),user1.getPassword(), user1.getAge(), "GRE10230910290194", "112233", "mersedes");
        //Driver2= kostas3
        Driver driver2= new Driver(user3.getUsername(),user3.getName(),user3.getSurname(),user3.getPhone(), user3.getEmail(),user3.getPassword(), user3.getAge(),"GRE10230910290333", "118899", "BMW");
        driverDAO.save(driver1);
        driverDAO.save(driver2);
        // NEW PASSENGERS////////////////////////////////////////////////////////////////
        PassengerDAO passengerDAO = getPassengerDAO();
        // Passenger1=markos_andre
        Passenger passenger1= new Passenger(user2.getUsername(),user2.getName(),user2.getSurname(),user2.getPhone(), user2.getEmail(),user2.getPassword(), user2.getAge(),"10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger1);

        //Passenger2= eleni_zanou

        Passenger passenger2= new Passenger(user4.getUsername(),user4.getName(),user4.getSurname(),user4.getPhone(), user4.getEmail(),user4.getPassword(), user4.getAge(),"1023091029099", "Eleni Zanou", "333");
        passengerDAO.save(passenger2);


        // NEW ROUTES //////////////////////////////////////////////////////////////////////
        RouteDAO routeDAO= getRouteDAO();
        final Currency euroCurrency = Currency.getInstance("EUR");
        Money money = new Money(10.0, euroCurrency);
        Address destination = new Address("mesogeiwn", "10", "athens", new ZipCode("16562"), "greece");
        Route route1 = new Route(driver1, money, LocalDateTime.of(2024, 1, 6, 10, 12), destination, 3, false);
//        route1.Completed();


        Money money5 = new Money(5.0, euroCurrency);
        Address destination1 = new Address("mesogeiwn", "157", "athens", new ZipCode("16571"), "greece");
        Route route2 = new Route(driver2, money5, LocalDateTime.of(2023, 10, 28, 16, 30), destination1, 2, false);

        Money money20 = new Money(20.0, euroCurrency);
        Address destination2 = new Address("tsimiski", "50", "thessaloniki", new ZipCode("54623"), "greece");
        Route route3 = new Route(driver1, money20, LocalDateTime.of(2024, 1, 2, 16, 30), destination2, 4, false);
//        route3.Completed();

        routeDAO.save(route1);
        routeDAO.save(route3);
        routeDAO.save(route2);

        driver1.addRoute(route1);
        driver1.addRoute(route3);
        driver2.addRoute(route2);

        passenger1.addRoute(route1);
        passenger2.addRoute(route1);
        passenger2.addRoute(route3);

        //NEW PASSENGER RATING//////////////////////////////////////////////////////////////

        PassengerRatingDao passengerRatingDao= getPassengerRatingDAO();

        ////// NEW SUBROUTES ///////////////////////////////////////////////////////////////////

        SubrouteDAO subrouteDAO= getSubrouteDAO();
        Address sub_destination1 = new Address("tsimiski", "56", "thessaloniki", new ZipCode("54626"), "greece");
        Address sub_pickuppoint1 = new Address("aetideon", "41", "athens", new ZipCode("15561"), "greece");
        Subroute subroute1= new Subroute(sub_destination1,sub_pickuppoint1,LocalDateTime.of(2023, 1, 28, 16, 30));
        subroute1.setStatus(Request_status.PENDING);

        Address sub_destination2 = new Address("patision", "147", "athens", new ZipCode("11257"), "greece");
        Address sub_pickuppoint2 = new Address("kleious", "4", "athens", new ZipCode("15561"), "greece");
        Subroute subroute2= new Subroute(sub_destination2,sub_pickuppoint2,LocalDateTime.of(2024, 1, 28, 16, 30));
        subroute2.setStatus(Request_status.APPROVED);

        subrouteDAO.save(subroute1);
        subrouteDAO.save(subroute2);

        route1.addPassengerRoute(passenger1,subroute2);
        route3.addPassengerRoute(passenger2,subroute1);

        // NEW DRIVER RAITING//////////////////////////////////////////////////////////////////////
        DriverRatingDAO driverRatingDAO=getDriverRatingDAO();
        DriverRating driver_rating1= new DriverRating(driver1,route1,"4.5","5.0","3.5");
        driverRatingDAO.save(driver_rating1);
        driver_rating1.addRate(passenger1,driver_rating1);
        driver1.addRates(driver_rating1);


   }
    public abstract UserDAO getUserDAO();

    public abstract DriverDAO getDriverDAO();

    public abstract PassengerDAO getPassengerDAO();

    public abstract PassengerRatingDao getPassengerRatingDAO();

    public abstract DriverRatingDAO getDriverRatingDAO();

    public abstract RouteDAO getRouteDAO();

    public abstract SubrouteDAO getSubrouteDAO();
}