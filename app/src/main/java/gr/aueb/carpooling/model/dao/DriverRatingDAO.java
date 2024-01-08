package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.DriverRating;

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


    /**
     * Retrieves and returns a list containing all DriverRating entities stored in the storage.
     *
     * @return An ArrayList containing all DriverRating entities.
     */
    ArrayList<DriverRating> findAll();

}
