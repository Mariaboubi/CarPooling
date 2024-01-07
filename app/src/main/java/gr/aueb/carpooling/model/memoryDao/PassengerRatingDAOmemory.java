package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;

public class PassengerRatingDAOmemory implements PassengerRatingDao {

    protected static ArrayList<PassengerRating> entities = new ArrayList<>();

    @Override
    public void delete(PassengerRating rating) {
        entities.remove(rating);
    }


    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public List<PassengerRating> findAll() {
        return new ArrayList<>(entities);}

    @Override
    public void save(PassengerRating entity) {
        entities.add(entity);
    }

    @Override
    public PassengerRating find(Passenger passenger) {
        for(PassengerRating rating: entities){
            if(passenger.equals(rating.getPassenger())){
                return rating;
            }
        }
        return null;
    }

    @Override
    public PassengerRating find(Route route) {
        for(PassengerRating rating: entities){
            if(route.equals(rating.getRoute())){
                return rating;
            }
        }
        return null;
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
}
