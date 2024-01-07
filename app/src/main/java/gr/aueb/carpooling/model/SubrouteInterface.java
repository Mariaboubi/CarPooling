package gr.aueb.carpooling.model;

import static gr.aueb.carpooling.model.Request_status.APPROVED;
import static gr.aueb.carpooling.model.Request_status.PENDING;
import static gr.aueb.carpooling.model.Request_status.REJECTED;

import org.threeten.bp.LocalDateTime;

import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;

/**
 * The SubrouteInterface represents the interface for a subroute within a carpooling system.
 */
public interface SubrouteInterface {
    /**
     * Get the destination address of the subroute.
     *
     * @return The destination address of the subroute
     */
    Address getDestination();

    /**
     * Get the unique identifier for the subroute.
     *
     * @return The subroute's identifier
     */
    int getId();

    /**
     * Get the cost associated with the subroute.
     *
     * @return The cost of the subroute
     */
    Money getCost();

    /**
     * Set the cost associated with the subroute.
     *
     * @param cost The cost to set for the subroute
     */
    void setCost(Money cost);

    /**
     * Set the destination address of the subroute.
     *
     * @param destination The destination address to set for the subroute
     */
    void setDestination(Address destination);

    /**
     * Get the pickup point address of the subroute.
     *
     * @return The pickup point address of the subroute
     */
    Address getPickupPoint();

    /**
     * Set the pickup point address of the subroute.
     *
     * @param pickupPoint The pickup point address to set for the subroute
     */
    void setPickupPoint(Address pickupPoint);

    /**
     * Get the pickup time of the subroute.
     *
     * @return The pickup time of the subroute
     */
    LocalDateTime getPickupTime();

    /**
     * Set the pickup time of the subroute.
     *
     * @param pickupTime The pickup time to set for the subroute
     */
    void setPickupTime(LocalDateTime pickupTime);

    /**
     * Calculate the cost of the subroute based on distance and a predefined cost per kilometer.
     *
     * @return The calculated cost of the subroute
     */
    Money calculateCost();


    void setStatus(Request_status status);


    Request_status getStatus();
}
