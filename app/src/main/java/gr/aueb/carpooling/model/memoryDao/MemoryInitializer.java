package gr.aueb.carpooling.model.memoryDao;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.dao.Initializer;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.dao.UserDAO;

public class MemoryInitializer extends Initializer {
    // Initializes memory DAO'S

    @Override
    public UserDAO getUserDAO() {return new UserDAOmemory();}

    @Override
    public DriverDAO getDriverDAO() {
        return new DriverDAOmemory();
    }

    @Override
    public PassengerDAO getPassengerDAO() {return new PassengerDAOmemory();}

    public PassengerRatingDao getPassengerRatingDAO() {return new PassengerRatingDAOmemory();}

    public DriverRatingDAO getDriverRatingDAO() {return new DriverRatingDAOmemory();}
    @Override
    public RouteDAO getRouteDAO() {
        return new RouteDAOmemory();
    }

    public SubrouteDAO getSubrouteDAO() {
        return new SubrouteDAOmemory();
    }

}
