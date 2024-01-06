package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;

public interface DriverRatingDAO {

    void delete(DriverRating entity);

    void deleteAll();

    void save(DriverRating entity);

    List<DriverRating> findAll();

    DriverRating findByPassenger(Passenger passenger);

    DriverRating find(Route route);
    DriverRating findByDriver(Driver driver);


}
