package gr.aueb.carpooling.model;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;

import org.threeten.bp.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

/**
 * The RouteInterface represents the interface for a route in a carpooling system.
 */
public interface RouteInterface {
    /**
     * Check if the route has been completed.
     *
     * @return True if the route is completed, false otherwise
     */
    boolean isCompleted();

    /**
     * Mark the route as completed.
     */
    void Completed();

    /**
     * Get the unique identifier for the route.
     *
     * @return The route's identifier
     */
    int getId();

    /**
     * Get the maximum number of passengers allowed on the route.
     *
     * @return The maximum number of passengers
     */
    int getMaxPassengers();

    /**
     * Set the maximum number of passengers allowed on the route.
     *
     * @param pass The maximum number of passengers to set
     */
    void setMaxPassengers(int pass);

    /**
     * Get the driver of the route.
     *
     * @return The driver of the route
     */
    Driver getDriver();

    /**
     * Get the total cost of the route.
     *
     * @return The total cost of the route
     */
    Money getTotalCost();

    /**
     * Set the driver of the route.
     *
     * @param driver The driver to set for the route
     */
    void setDriver(Driver driver);

    /**
     * Get a mapping of passengers to their subroutes on the route.
     *
     * @return A HashMap of passengers to their subroutes
     */
    HashMap<Passenger, Subroute> getPassengerRoutes();

    /**
     * Get the set of passengers on the route.
     *
     * @return The set of passengers on the route
     */
    Set<Passenger> getPassengers();

    /**
     * Add a passenger and their subroute to the route.
     *
     * @param passenger The passenger to add
     * @param subroute  The subroute for the passenger
     */
    void addPassenger(Passenger passenger, Subroute subroute);


    /**
     * Get the subroute associated with a passenger.
     *
     * @param passenger The passenger for whom to retrieve the subroute.
     * @return The subroute for the passenger.
     */
    Subroute getSubRouteByPassenger(Passenger passenger);

    /**
     * Remove a passenger and their subroute from the route.
     *
     * @param passenger The passenger to remove
     */
    void removePassenger(Passenger passenger);


    /**
     * Get the cost of a specific passenger on the route.
     *
     * @param passenger The passenger to calculate the cost for
     * @return The cost of the passenger's subroute
     */
    Money getPassengerCost(Passenger passenger);

    /**
     * Get the date and time of the route.
     *
     * @return The date and time of the route
     */
    LocalDateTime getDate();

    /**
     * Set the date and time of the route.
     *
     * @param date The date and time to set for the route
     */
    void setDate(LocalDateTime date);

    /**
     * Get the destination address of the route.
     *
     * @return The destination address of the route
     */
    Address getDestination();


    String getDestinationString();

    /**
     * Set the destination address of the route.
     *
     * @param destination The destination address to set for the route
     */
    void setDestination(Address destination);

    /**
     * Get the estimated cost of the route.
     *
     * @return The estimated cost of the route
     */
    Money getEstimatedCost();

    /**
     * Set the estimated cost of the route.
     *
     * @param estimated_cost The estimated cost to set for the route
     */
    void setEstimatedCost(Money estimated_cost);

    /**
     * Calculate the total cost of the route based on passenger subroutes.
     *
     */
    void calculateTotalCost();
}


