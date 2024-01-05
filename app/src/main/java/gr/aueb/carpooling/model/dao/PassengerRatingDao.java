package gr.aueb.carpooling.model.dao;

import java.util.List;
import java.util.Set;


import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;

public interface PassengerRatingDao {
    void delete(PassengerRating rating);


    void deleteAll();

    List<PassengerRating> findAll();

    void save(PassengerRating entity);



    PassengerRating find(Passenger passenger);

    PassengerRating find(Route route);
}
