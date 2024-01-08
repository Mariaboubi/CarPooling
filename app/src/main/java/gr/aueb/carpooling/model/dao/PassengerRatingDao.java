package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;

/**
 * The PassengerRatingDao interface defines the contract for classes that
 * manage the storage and retrieval of passenger ratings.
 */
public interface PassengerRatingDao {

    /**
     * Deletes all passenger ratings from the storage.
     */
    void deleteAll();

    /**
     * Saves a PassengerRating entity to the storage.
     *
     * @param entity The PassengerRating entity to be saved.
     */
    void save(PassengerRating entity);

    /**
     * Finds and returns a list of PassengerRating entities associated with the specified route.
     *
     * @param route The route for which passenger ratings are to be retrieved.
     * @return The list of PassengerRating entities associated with the specified route.
     */
    ArrayList<PassengerRating> findAllByRoute(Route route);



    /**
     * Retrieves and returns a list containing all PassengerRating entities stored in the storage.
     *
     * @return An ArrayList containing all PassengerRating entities.
     */
    ArrayList<PassengerRating> findAll();

}

