package gr.aueb.carpooling.model;

/**
 * The DriverRatingInterface represents the interface for rating a driver's performance.
 */
public interface DriverRatingInterface {
    /**
     * Set the security rating for the driver.
     *
     * @param securityRating The security rating to be set
     */
    void setSecurityRating(float securityRating);

    /**
     * Get the security rating for the driver.
     *
     * @return The security rating
     */
    float getSecurityRating();

    /**
     * Set the cleanliness rating for the driver.
     *
     * @param cleanlinessRating The cleanliness rating to be set
     */
    void setCleanlinessRating(float cleanlinessRating);

    /**
     * Get the cleanliness rating for the driver.
     *
     * @return The cleanliness rating
     */
    float getCleanlinessRating();

    /**
     * Calculate and return the average rating for the driver.
     *
     * @return The average rating
     */
    float averageRating();
}

