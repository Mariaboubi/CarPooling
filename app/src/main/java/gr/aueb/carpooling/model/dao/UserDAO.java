package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.User;

/**
 * The UserDAO interface defines the contract for classes that
 * manage the storage and retrieval of user entities.
 */
public interface UserDAO {

    /**
     * Deletes all user entities from the storage.
     */
    void deleteAll();

    /**
     * Saves a User entity to the storage.
     *
     * @param entity The User entity to be saved.
     */
    void save(User entity);

    /**
     * Finds and returns a User entity based on the given username and password.
     *
     * @param username The username of the user to find.
     * @param password The password of the user to find.
     * @return The User entity with the specified username and password, or null if not found.
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * Finds and returns a User entity based on the given username.
     *
     * @param username The username of the user to find.
     * @return The User entity with the specified username, or null if not found.
     */
    User findByUsername(String username);


    /**
     * Retrieves and returns a list containing all User entities stored in the storage.
     *
     * @return An ArrayList containing all User entities.
     */
    ArrayList<User> findAll();
}
