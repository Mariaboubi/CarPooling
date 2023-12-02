package carPooling;
import java.sql.Time;

public interface RequestInterface {
    
    /**
     * Gets the passenger associated with this request.
     *
     * @return The passenger associated with this request.
     */
    Passenger getPassenger();

    /**
     * Sets the passenger associated with this request.
     *
     * @param passenger The passenger to be associated with this request.
     */
    void setPassenger(Passenger passenger);

    /**
     * Gets the driver associated with this request.
     *
     * @return The driver associated with this request.
     */
    Driver getDriver();

    /**
     * Sets the driver associated with this request.
     *
     * @param driver The driver to be associated with this request.
     */
    void setDriver(Driver driver);

    /**
     * Gets the destination address of this request.
     *
     * @return The destination address of this request.
     */
    Address getDestination();

    /**
     * Sets the destination address of this request.
     *
     * @param destination The destination address to be set for this request.
     */
    void setDestination(Address destination);

    /**
     * Gets the pickup point address of this request.
     *
     * @return The pickup point address of this request.
     */
    Address getPickupPoint();

    /**
     * Sets the pickup point address of this request.
     *
     * @param pickupPoint The pickup point address to be set for this request.
     */
    void setPickupPoint(Address pickupPoint);

    /**
     * Gets the pickup time of this request.
     *
     * @return The pickup time of this request.
     */
    Time getPickupTime();

    /**
     * Sets the pickup time of this request.
     *
     * @param pickupTime The pickup time to be set for this request.
     */
    void setPickupTime(Time pickupTime);

    /**
     * Gets the status of this request.
     *
     * @return The status of this request.
     */
    RequestStatus getRequestStatus();

    /**
     * Sets the status of this request.
     *
     * @param requestStatus The status to be set for this request.
     */
    void setRequestStatus(RequestStatus requestStatus);
}
    


