package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.Driver;

/**
 * The DriverDAO interface defines the contract for classes that
 * manage the storage and retrieval of driver entities.
 */
public interface DriverDAO {

    /**
     * Deletes all driver entities from the storage.
     */
    void deleteAll();

    /**
     * Saves a Driver entity to the storage.
     *
     * @param entity The Driver entity to be saved.
     */
    void save(Driver entity);

    /**
     * Checks if a driver with the specified username exists in the storage.
     *
     * @param username The username of the driver to check.
     * @return true if the driver with the given username exists; otherwise, false.
     */
    boolean find(String username);

    /**
     * Finds and returns a Driver entity based on the given username.
     *
     * @param username The username of the driver to find.
     * @return The Driver entity with the specified username, or null if not found.
     */
    Driver findByUsername(String username);

    /**
     * Finds and returns a Driver entity based on the given user ID.
     *
     * @param id The user ID of the driver to find.
     * @return The Driver entity with the specified user ID, or null if not found.
     */
    Driver find(int id);

    /**
     * Checks if a driver with the specified driver ID exists in the storage.
     *
     * @param id The driver ID of the driver to check.
     * @return true if the driver with the given driver ID exists; otherwise, false.
     */
    boolean findDriver(int id);

    /**
     * Retrieves and returns a list containing all Driver entities stored in the storage.
     *
     * @return An ArrayList containing all Driver entities.
     */
    ArrayList<Driver> findAll();

}

