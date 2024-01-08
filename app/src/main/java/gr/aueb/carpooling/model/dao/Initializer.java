package gr.aueb.carpooling.model.dao;

import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
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
        EmailAddress email5 = new EmailAddress("georgesp@gmail.com");

        User user1= new User("maria123","maria","pappa","6900000000",email1,"12345678","25");
        User user2= new User("markos_andre","markos","andreopoulos","6972169794",email2,"11111111","27");
        User user3= new User("kostas3","kostas","Papadopoulos","6972169794",email3,"12121212","21");
        User user4= new User("eleniz","eleni","Zanou","6977292186",email4,"11111111","20");
        User user5 = new User("george_sp","George","Spathis","6996856363",email4,"33333333","30"); //just a user

        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
        userDAO.save(user4);
        userDAO.save(user5);

        //// NEW DRIVERS////////////////////////////////////////////////////////////////
        DriverDAO driverDAO = getDriverDAO();

        // Driver1 = maria123
        Driver driver1= new Driver(user1.getUsername(),user1.getName(),user1.getSurname(),user1.getPhone(), user1.getEmail(),user1.getPassword(), user1.getAge(), "GRE10230910290194", "112233", "mercedes");
        // Driver2 = kostas3
        Driver driver2= new Driver(user3.getUsername(),user3.getName(),user3.getSurname(),user3.getPhone(), user3.getEmail(),user3.getPassword(), user3.getAge(),"GRE10230910290333", "118899", "BMW");

        driverDAO.save(driver1);
        driverDAO.save(driver2);

        //// NEW PASSENGERS////////////////////////////////////////////////////////////////
        PassengerDAO passengerDAO = getPassengerDAO();

        // Passenger1 = markos_andre
        Passenger passenger1= new Passenger(user2.getUsername(),user2.getName(),user2.getSurname(),user2.getPhone(), user2.getEmail(),user2.getPassword(), user2.getAge(),"10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger1);

        // Passenger2 = eleni_z

        Passenger passenger2= new Passenger(user4.getUsername(),user4.getName(),user4.getSurname(),user4.getPhone(), user4.getEmail(),user4.getPassword(), user4.getAge(),"1023091029099", "Eleni Zanou", "333");
        passengerDAO.save(passenger2);


        //// NEW ROUTES //////////////////////////////////////////////////////////////////////
        RouteDAO routeDAO= getRouteDAO();

        Currency euroCurrency = Currency.getInstance("EUR");
        Money money = new Money(10.0, euroCurrency);
        Address destination = new Address("Mesogeiwn", "10", "Athens", new ZipCode("16562"), "greece");
        Route route1 = new Route(driver1, money, LocalDateTime.of(2024, 2, 6, 10, 12), destination, 3, false);


        Money money5 = new Money(5.0, euroCurrency);
        Address destination1 = new Address("Mesogeiwn", "157", "Athens", new ZipCode("16571"), "greece");
        Route route2 = new Route(driver2, money5, LocalDateTime.of(2024, 2, 28, 16, 30), destination1, 2, false);

        Money money20 = new Money(20.0, euroCurrency);
        Address destination2 = new Address("Tsimiski", "50", "Thessaloniki", new ZipCode("54623"), "greece");
        Route route3 = new Route(driver1, money20, LocalDateTime.of(2024, 3, 2, 16, 30), destination2, 4, false);

        Money money60 = new Money(60.0, euroCurrency);
        Address destination3 = new Address("Agiou Georgiou", "50", "Patra", new ZipCode("98009"), "greece");
        Route route4 = new Route(driver2, money60, LocalDateTime.of(2024, 2, 15, 16, 30), destination3, 4, false);

        routeDAO.save(route1);
        routeDAO.save(route3);
        routeDAO.save(route2);
        routeDAO.save(route4);

        driver1.addRoute(route1);
        driver1.addRoute(route3);
        driver2.addRoute(route2);

        passenger1.addRoute(route1);
        passenger1.addRoute(route2);

        passenger2.addRoute(route1);
        passenger2.addRoute(route3);

        passenger1.addRoute(route4);
        route4.Completed();

        ///// NEW PASSENGER RATING//////////////////////////////////////////////////////////////

        PassengerRatingDao passengerRatingDao= getPassengerRatingDAO();
        // Driver 2 rates passenger 1
        PassengerRating pass_rating = new PassengerRating(passenger1,route2,"4.5","5.0","3.5");
        passengerRatingDao.save(pass_rating);
        passenger1.addRating(pass_rating);


        //// NEW SUBROUTES ///////////////////////////////////////////////////////////////////
        SubrouteDAO subrouteDAO= getSubrouteDAO();

        Address sub_destination1 = new Address("Tsimiski", "56", "Thessaloniki", new ZipCode("54626"), "greece");
        Address sub_pickuppoint1 = new Address("Aetideon", "41", "Athens", new ZipCode("15561"), "greece");

        Subroute subroute1= new Subroute(sub_destination1,sub_pickuppoint1,LocalDateTime.of(2024, 1, 28, 16, 30));
        subroute1.setStatus(Request_status.PENDING);

        Address sub_destination2 = new Address("Patision", "147", "Athens", new ZipCode("11257"), "greece");
        Address sub_pickuppoint2 = new Address("Kleious", "4", "Athens", new ZipCode("15561"), "greece");

        Subroute subroute2= new Subroute(sub_destination2,sub_pickuppoint2,LocalDateTime.of(2024, 1, 28, 16, 30));
        subroute2.setStatus(Request_status.APPROVED);

        Address sub_destination3 = new Address("Xaras", "12", "Xalkida", new ZipCode("11456"), "greece");
        Address sub_pickuppoint3 = new Address("Euelpidon", "9", "Athens", new ZipCode("15561"), "greece");
        Subroute subroute3 = new Subroute(sub_destination3,sub_pickuppoint3,LocalDateTime.of(2024, 1, 28, 16, 30));
        subrouteDAO.save(subroute1);
        subrouteDAO.save(subroute2);
        subrouteDAO.save(subroute3);

        route1.addPassengerRoute(passenger1,subroute2);
        route3.addPassengerRoute(passenger2,subroute1);

        route3.addPassengerRoute(passenger1,subroute3);
        subroute3.setStatus(Request_status.COMPLETED);

        ///// NEW DRIVER RATING//////////////////////////////////////////////////////////////////////
        // Passenger 1 rates driver 1
        DriverRatingDAO driverRatingDAO = getDriverRatingDAO();

        DriverRating driver_rating1 = new DriverRating(driver1,route1,"4.5","5.0","3.5");
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