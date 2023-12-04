package gr.aueb.carpooling.model;

public class PassengerRating extends Rating implements PassengerRatingInterface{
    private float consistencyRating; // Rating for consistency issues
    private float reliabilityRating; // Rating for reliability issues

    // Constructor
    public PassengerRating(Passenger passenger, Route route, float politenessRating,
                           float consistencyRating, float reliabilityRating) throws IllegalArgumentException {
        super(passenger, route, politenessRating);
        validateRating(consistencyRating, "Consistency");
        validateRating(reliabilityRating, "Reliability");
        this.consistencyRating = consistencyRating;
        this.reliabilityRating = reliabilityRating;
    }

    public void setConsistencyRating(float consistencyRating) throws IllegalArgumentException {
        validateRating(consistencyRating, "Consistency");
        this.consistencyRating = consistencyRating;
    }

    public float getConsistencyRating() {
        return consistencyRating;
    }

    public void setReliabilityRating(float reliabilityRating) throws IllegalArgumentException {
        validateRating(reliabilityRating, "Reliability");
        this.reliabilityRating = reliabilityRating;
    }

    public float getReliabilityRating() {
        return reliabilityRating;
    }

    public float averageRating() {
        float sum = this.reliabilityRating + this.getPolitenessRating() + this.consistencyRating;
        return sum / 3;
    }

    // Validates a user rating to ensure it falls within the specified range.
    private void validateRating(float rating, String ratingName) throws IllegalArgumentException {
        // Check if the given rating is outside the valid range defined by AppGlobals
        if (rating < AppGlobals.MIN_RATING || rating > AppGlobals.MAX_RATING) {
            // If the rating is outside the valid range, throw an exception with a descriptive error message.
            throw new IllegalArgumentException(
                    ratingName + " rating must be between " + AppGlobals.MIN_RATING + " and " + AppGlobals.MAX_RATING);
        }
    }
}
