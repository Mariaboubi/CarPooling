package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;


import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;

public interface PassengerRatingDao {

    void deleteAll();

    List<PassengerRating> findAll();

    void save(PassengerRating entity);


    ArrayList<PassengerRating> findAllByRoute(Route route);
}
