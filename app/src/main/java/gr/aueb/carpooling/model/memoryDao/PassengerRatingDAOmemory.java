package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;

public class PassengerRatingDAOmemory implements PassengerRatingDao {

    protected static ArrayList<PassengerRating> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(PassengerRating entity) {
        entities.add(entity);
    }
    @Override
    public ArrayList<PassengerRating> findAllByRoute(Route route) {
        ArrayList<PassengerRating> ratings = new ArrayList<>();
        for(PassengerRating rating: entities){
            if(route.equals(rating.getRoute())){
                ratings.add(rating);
            }
        }
        return ratings;
    }

    @Override
    public ArrayList<PassengerRating> findAll() {
        return entities;
    }
}
