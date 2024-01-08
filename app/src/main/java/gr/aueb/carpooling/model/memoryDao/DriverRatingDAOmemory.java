package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;

public class DriverRatingDAOmemory implements DriverRatingDAO {

    protected static ArrayList<DriverRating> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(DriverRating entity) {
        entities.add(entity);
    }

}
