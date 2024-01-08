package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;

/**
 * The PassengerDAO interface defines the contract for classes that
 * manage the storage and retrieval of passenger entities.
 */
public interface PassengerDAO {

    /**
     * Deletes all passenger entities from the storage.
     */
    void deleteAll();

    /**
     * Saves a Passenger entity to the storage.
     *
     * @param entity The Passenger entity to be saved.
     */
    void save(Passenger entity);

    /**
     * Checks if a passenger with the specified username exists in the storage.
     *
     * @param username The username of the passenger to check.
     * @return true if the passenger with the given username exists; otherwise, false.
     */
    boolean find(String username);

    /**
     * Finds and returns a Passenger entity based on the given username.
     *
     * @param username The username of the passenger to find.
     * @return The Passenger entity with the specified username, or null if not found.
     */
    Passenger findByUsername(String username);

    /**
     * Finds and returns a list of Passenger entities that have the specified route.
     *
     * @param route The route to filter passengers.
     * @return The list of Passenger entities with the specified route.
     */
    ArrayList<Passenger> findAllByRoute(Route route);

    /**
     * Finds and returns a Passenger entity based on the given user ID.
     *
     * @param id The user ID of the passenger to find.
     * @return The Passenger entity with the specified user ID, or null if not found.
     */
    Passenger find(int id);

    /**
     * Checks if a passenger with the specified passenger ID exists in the storage.
     *
     * @param id The passenger ID of the passenger to check.
     * @return true if the passenger with the given passenger ID exists; otherwise, false.
     */
    boolean findPassenger(int id);

    /**
     * Retrieves and returns a list containing all Passenger entities stored in the storage.
     *
     * @return An ArrayList containing all Passenger entities.
     */
    ArrayList<Passenger> findAll();

}

