package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.Collection;

import gr.aueb.carpooling.model.Subroute;

/**
 * The SubrouteDAO interface defines the contract for classes that
 * manage the storage and retrieval of subroute entities.
 */
public interface SubrouteDAO {

    /**
     * Deletes a specific Subroute entity from the storage.
     *
     * @param entity The Subroute entity to be deleted.
     */
    void delete(Subroute entity);

    /**
     * Deletes all subroute entities from the storage.
     */
    void deleteAll();

    /**
     * Saves a Subroute entity to the storage.
     *
     * @param entity The Subroute entity to be saved.
     */
    void save(Subroute entity);

    /**
     * Finds and returns a Subroute entity based on the given ID.
     *
     * @param id The ID of the subroute to find.
     * @return The Subroute entity with the specified ID, or null if not found.
     */
    Subroute findById(int id);


    /**
     * Retrieves and returns a list containing all Subroute entities stored in the storage.
     *
     * @return An ArrayList containing all Subroute entities.
     */
    ArrayList<Subroute> findAll();

}

