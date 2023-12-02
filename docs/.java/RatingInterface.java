public interface RatingInterface {

    /**
     * Gets the politeness rating of the user.
     *
     * @return The politeness rating.
     */
    float getPolitenessRating();

    /**
     * Sets the politeness rating of the user.
     *
     * @param politenessRating The politeness rating to be set.
     * @throws IllegalArgumentException If the provided rating is outside the valid range [0, 5].
     */
    void setPolitenessRating(float politenessRating) throws IllegalArgumentException;

}
