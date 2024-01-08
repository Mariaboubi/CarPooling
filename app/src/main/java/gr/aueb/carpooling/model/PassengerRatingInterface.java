package gr.aueb.carpooling.model;

/**
 * The PassengerRatingInterface represents the interface for rating a passenger's performance.
 */
public interface PassengerRatingInterface {
    /**
     * Set the consistency rating for the passenger.
     *
     * @param consistencyRating The consistency rating to be set
     * @throws IllegalArgumentException If the rating is not within the valid range
     */
    void setConsistencyRating(String consistencyRating) ;

    /**
     * Get the consistency rating for the passenger.
     *
     * @return The consistency rating
     */
    String getConsistencyRating();

    /**
     * Set the reliability rating for the passenger.
     *
     * @param reliabilityRating The reliability rating to be set
     * @throws IllegalArgumentException If the rating is not within the valid range
     */
    void setReliabilityRating(String reliabilityRating) ;

    /**
     * Get the reliability rating for the passenger.
     *
     * @return The reliability rating
     */
    String getReliabilityRating();

    /**
     * Calculate and return the average rating for the passenger.
     *
     * @return The average rating
     */
    float averageRating();
}

