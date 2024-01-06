package gr.aueb.carpooling.model;

import java.util.HashMap;

/**
 * The DriverRatingInterface represents the interface for rating a driver's performance.
 */
public interface DriverRatingInterface {
    /**
     * Set the security rating for the driver.
     *
     * @param securityRating The security rating to be set
     */
    void setSecurityRating(String securityRating);

    void addRate(Passenger pas,DriverRating rate);

    HashMap<Passenger,DriverRating> getPassengersRates();

    /**
     * Get the security rating for the driver.
     *
     * @return The security rating
     */
    String getSecurityRating();

    /**
     * Set the cleanliness rating for the driver.
     *
     * @param cleanlinessRating The cleanliness rating to be set
     */
    void setCleanlinessRating(String cleanlinessRating);

    /**
     * Get the cleanliness rating for the driver.
     *
     * @return The cleanliness rating
     */
    String getCleanlinessRating();

    /**
     * Calculate and return the average rating for the driver.
     *
     * @return The average rating
     */
    float averageRating();
}

