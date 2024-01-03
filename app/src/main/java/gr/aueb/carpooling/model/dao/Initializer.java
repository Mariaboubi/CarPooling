package gr.aueb.carpooling.model.dao;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.contact.EmailAddress;

public abstract class Initializer {
    public void eraseAll() {
        UserDAO userDAO = getUserDAO();
        userDAO.deleteAll();
        DriverDAO driverDAO = geDriverDAO();
        driverDAO.deleteAll();
        PassengerDAO passengerDAO = getPassengerDAO();
        passengerDAO.deleteAll();

    }


    public void prepareData() {
        eraseAll();
        // NEW USERS/////////////////////////////////////////////////////////////////
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
        // NEW DRIVERS////////////////////////////////////////////////////////////////
        DriverDAO driverDAO= geDriverDAO();
//        Driver driver1= new Driver("maria123","maria","pappa","6900000000",email1,"1234","25","GRE10230910290194", "112233", "mersedes");
        Driver driver2= new Driver("markos_andre","markos","andreopoulos","6972169794",email2,"1111","27","GRE10230910290333", "118899", "BMW");
//        driverDAO.save(driver1);
        driverDAO.save(driver2);
        // NEW PASSENGERS////////////////////////////////////////////////////////////////
        PassengerDAO passengerDAO = getPassengerDAO();
        Passenger passenger1= new Passenger("maria123","maria","pappa","6900000000",email1,"1234","25","10230910290194", "Maria Volt", "365");
        Passenger passenger2= new Passenger("markos_andre","markos","andreopoulos","6972169794",email2,"1111","27","10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger1);
        passengerDAO.save(passenger2);

    }
    public abstract UserDAO getUserDAO();

    public abstract DriverDAO geDriverDAO();

    public abstract PassengerDAO getPassengerDAO();
}