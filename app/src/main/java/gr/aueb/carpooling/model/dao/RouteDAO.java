package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

/**
 * The RouteDAO interface defines the contract for classes that
 * manage the storage and retrieval of route entities.
 */
public interface RouteDAO {

    /**
     * Deletes all route entities from the storage.
     */
    void deleteAll();

    /**
     * Saves a Route entity to the storage.
     *
     * @param entity The Route entity to be saved.
     */
    void save(Route entity);

    /**
     * Retrieves all Route entities from the storage.
     *
     * @return A list of all Route entities.
     */
    ArrayList<Route> findAll();

    /**
     * Finds and returns a Route entity based on the given passenger and subroute.
     *
     * @param pas The passenger for which the route is associated.
     * @param sub The subroute for which the route is associated.
     * @return The Route entity associated with the specified passenger and subroute, or null if not found.
     */
    Route findRouteByPassAndSub(Passenger pas, Subroute sub);

    /**
     * Finds and returns a list of Subroute entities associated with the specified passenger.
     *
     * @param passenger The passenger for which subroutes are to be retrieved.
     * @return The list of Subroute entities associated with the specified passenger.
     */
    ArrayList<Subroute> findSubroutesByPassenger(Passenger passenger);

    /**
     * Finds and returns a list of completed Subroute entities associated with the specified passenger.
     *
     * @param passenger The passenger for which completed subroutes are to be retrieved.
     * @return The list of completed Subroute entities associated with the specified passenger.
     */
    ArrayList<Subroute> findSubroutesByPassengerIsCompleted(Passenger passenger);

    /**
     * Finds and returns a Route entity based on the given route ID.
     *
     * @param id The route ID to find.
     * @return The Route entity with the specified route ID, or null if not found.
     */
    Route find(int id);

    /**
     * Finds and returns a list of Route entities associated with the specified driver.
     *
     * @param driver The driver for which routes are to be retrieved.
     * @return The list of Route entities associated with the specified driver.
     */
    ArrayList<Route> findByDriver(Driver driver);

    /**
     * Finds and returns a list of completed Route entities associated with the specified driver.
     *
     * @param driver The driver for which completed routes are to be retrieved.
     * @return The list of completed Route entities associated with the specified driver.
     */
    ArrayList<Route> findByDriverIsCompleted(Driver driver);

    /**
     * Finds and returns the passenger associated with the specified subroute.
     *
     * @param subroute The subroute for which the associated passenger is to be retrieved.
     * @return The Passenger entity associated with the specified subroute, or null if not found.
     */
    Passenger findPassengerBySubroute(Subroute subroute);

    /**
     * Finds and returns the Route entity associated with the specified subroute.
     *
     * @param subroute The subroute for which the associated Route is to be retrieved.
     * @return The Route entity associated with the specified subroute, or null if not found.
     */
    Route findRouteBySubroute(Subroute subroute);

}
