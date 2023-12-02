public interface PassengerRatingInterface {
/**
     * Gets the consistency rating of the passenger.
     *
     * @return The consistency rating.
     */
    float getConsistencyRating();

    /**
     * Sets the consistency rating of the passenger.
     *
     * @param consistencyRating The consistency rating to be set.
     * @throws IllegalArgumentException If the provided rating is outside the valid range [0, 5].
     */
    void setConsistencyRating(float consistencyRating) throws IllegalArgumentException;

    /**
     * Gets the reliability rating of the passenger.
     *
     * @return The reliability rating.
     */
    float getReliabilityRating();

    /**
     * Sets the reliability rating of the passenger.
     *
     * @param reliabilityRating The reliability rating to be set.
     * @throws IllegalArgumentException If the provided rating is outside the valid range [0, 5].
     */
    void setReliabilityRating(float reliabilityRating) throws IllegalArgumentException;
}


