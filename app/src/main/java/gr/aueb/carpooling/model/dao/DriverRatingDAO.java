package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;

/**
 * The DriverRatingDAO interface defines the contract for classes that
 * manage the storage and retrieval of driver ratings.
 */
public interface DriverRatingDAO {

    /**
     * Deletes all driver ratings from the storage.
     */
    void deleteAll();

    /**
     * Saves a DriverRating entity to the storage.
     *
     * @param entity The DriverRating entity to be saved.
     */
    void save(DriverRating entity);
}
