package gr.aueb.carpooling.model;

import java.util.HashSet;

import gr.aueb.carpooling.model.contact.EmailAddress;

/**
 * Interface for Driver functionalities in a transportation application.
 */
public interface DriverInterface {

    /**
     * Changes the personal details of the driver.
     *
     * @param username The new username of the driver.
     * @param name The new name of the driver.
     * @param surname The new surname of the driver.
     * @param phone The new phone number of the driver.
     * @param email The new email address of the driver.
     * @param age The new age of the driver.
     * @param licenseNumber The new license number of the driver.
     * @param carType The new car type of the driver.
     */
    void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email,
                               String age, String licenseNumber, String carType);

    /**
     * Getter method to retrieve the IBAN of the driver.
     *
     * @return The IBAN of the driver
     */
    String getIban();

    /**
     * Getter method to retrieve the driver's license number.
     *
     * @return The driver's license number
     */
    String getLicenseNumber();

    /**
     * Getter method to retrieve the type of car the driver uses.
     *
     * @return The type of car the driver uses
     */
    String getCarType();

    /**
     * Changes the IBAN of the driver.
     *
     * @param iban The new IBAN to be set for the driver (must not be null)
     * @throws NullPointerException if the provided IBAN is null
     */
    void changeIban(String iban) throws NullPointerException;

    /**
     * Method to change the driver's license number.
     *
     * @param license_num The new driver's license number (must not be null)
     * @throws NullPointerException if the provided license number is null
     */
    void changeLicenseNumber(String license_num) throws NullPointerException;

    /**
     * Method to change the type of car the driver uses.
     *
     * @param type The new type of car the driver uses (must not be null)
     * @throws NullPointerException if the provided car type is null
     */
    void changeCarType(String type) throws NullPointerException;

    /**
     * Add a route to the driver's set of routes.
     *
     * @param route The route to be added.
     */
    void addRoute(Route route);

    //void addPassengerRating(PassengerRating rating);

    /**
     * Remove a route from the driver's set of routes.
     *
     * @param route The route to be removed.
     */
    void removeRoute(Route route);

    //void removePassengerRating(PassengerRating rating) ;

    /**
     * Check if the driver has a specific route.
     *
     * @param route The route to check for.
     * @return True if the driver has the route, false otherwise.
     */
    boolean hasRoute(Route route);

    //boolean hasPassengerRating(PassengerRating rating);

    /**
     * Get the set of routes associated with the driver.
     *
     * @return The set of routes associated with the driver.
     */
    HashSet<Route> getRoutes();

}
