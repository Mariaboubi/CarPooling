package gr.aueb.carpooling.model;

public class PassengerRating extends Rating implements PassengerRatingInterface{
    private float consistencyRating; // Rating for consistency issues
    private float reliabilityRating; // Rating for reliability issues

    private Passenger passenger;

    // Constructor
    public PassengerRating(Passenger passenger, Route route, String politenessRating,
                           String consistencyRating, String reliabilityRating) throws IllegalArgumentException {
        super(passenger, route, Float.valueOf(politenessRating));
        validateRating(Float.valueOf(consistencyRating), "Consistency");
        validateRating(Float.valueOf(reliabilityRating), "Reliability");
        this.consistencyRating = Float.valueOf(consistencyRating);
        this.reliabilityRating = Float.valueOf(reliabilityRating);
        this.passenger=passenger;
    }

    public Passenger getPassenger(){
        return passenger;
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
