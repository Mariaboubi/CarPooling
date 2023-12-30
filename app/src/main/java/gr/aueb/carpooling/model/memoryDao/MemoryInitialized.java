package gr.aueb.carpooling.model.memoryDao;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.Initializer;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.UserDAO;

public class MemoryInitialized extends Initializer {
    @Override
    public UserDAO getUserDAO() {

        return new UserDAOmemory();
    }

    @Override
    public DriverDAO geDriverDAO() {
        return new DriverDAOmemory();
    }

    public RouteDAO geRouteDAO() {
        return new RouteDAOmemory();
    }

}
