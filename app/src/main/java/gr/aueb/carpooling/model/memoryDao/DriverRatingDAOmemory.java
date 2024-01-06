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
    public void delete(DriverRating entity) {
        entities.remove(entity);
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(DriverRating entity) {
        entities.add(entity);
    }

    @Override
    public List<DriverRating> findAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public DriverRating findByPassenger(Passenger passenger) {
        return null;
    }

    @Override
    public DriverRating find(Route route) {
        for(DriverRating rating: entities){
            if(route.equals(rating.getRoute())){
                return rating;
            }
        }
        return null;
    }

    @Override
    public DriverRating findByDriver(Driver driver) {
        for(DriverRating rating: entities){
            if(driver.equals(rating.getDriver())){
                return rating;
            }
        }
        return null;
    }
}
