package gr.aueb.carpooling.model;

/**
 * The RatingInterface represents the interface for rating a user's performance.
 */
public interface RatingInterface {

    /**
     * Get the politeness rating for the user.
     *
     * @return The politeness rating
     */
    float getPolitenessRating();

    /**
     * Set the politeness rating for the user.
     *
     * @param politenessRating The politeness rating to be set
     * @throws IllegalArgumentException If the rating is not within the valid range
     */
    void setPolitenessRating(float politenessRating) throws IllegalArgumentException;
}

