package carPooling;

import java.util.List;

public interface RouteInterface {

    /**
     * Gets the driver associated with the route.
     *
     * @return The driver associated with the route.
     */
    Driver getDriver();

    /**
     * Sets the driver for the route.
     *
     * @param driver The driver to be associated with the route.
     */
    void setDriver(Driver driver);

    /**
     * Gets the list of passengers for the route.
     *
     * @return The list of passengers for the route.
     */
    List<Passenger> getPassengers();

    /**
     * Sets the list of passengers for the route.
     *
     * @param passengers The list of passengers to be associated with the route.
     */
    void setPassengers(List<Passenger> passengers);

    /**
     * Gets the route data for the route.
     *
     * @return The route data for the route.
     */
    RouteData getRouteData();

    /**
     * Sets the route data for the route.
     *
     * @param routeData The route data to be associated with the route.
     */
    void setRouteData(RouteData routeData);

    /**
     * Calculates the cost of the route.
     *
     * @return The cost of the route as a Money object.
     */
    Money calculateCost();

}

