package gr.aueb.carpooling.model;

public class PassengerRating extends Rating implements PassengerRatingInterface{
    private String consistencyRating; // Rating for consistency issues
    private  String reliabilityRating; // Rating for reliability issues

    private Passenger passenger;

    // Constructor
    public PassengerRating(Passenger passenger, Route route, String politenessRating,
                           String consistencyRating, String reliabilityRating) throws IllegalArgumentException {
        super(passenger, route, politenessRating);
//        validateRating(consistencyRating, "Consistency");
//        validateRating(reliabilityRating, "Reliability");
        this.consistencyRating = consistencyRating;
        this.reliabilityRating = reliabilityRating;
        this.passenger=passenger;
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public void setConsistencyRating(String consistencyRating) throws IllegalArgumentException {
        //validateRating(consistencyRating, "Consistency");
        this.consistencyRating = consistencyRating;
    }

    public String getConsistencyRating() {
        return consistencyRating;
    }

    public void setReliabilityRating(String reliabilityRating) throws IllegalArgumentException {
        //validateRating(reliabilityRating, "Reliability");
        this.reliabilityRating = reliabilityRating;
    }

    public String getReliabilityRating() {
        return reliabilityRating;
    }

    public float averageRating() {
        float rel_rating = Float.parseFloat(this.reliabilityRating);
        float pol_rating = Float.parseFloat(this.getPolitenessRating());
        float const_rating = Float.parseFloat(this.consistencyRating);
        float sum = rel_rating + pol_rating+ const_rating;
        return sum / 3;
    }

    // Validates a user rating to ensure it falls within the specified range.
    private void validateRating(String rating, String ratingName) throws IllegalArgumentException {
        float r = Float.parseFloat(rating);
        // Check if the given rating is outside the valid range defined by AppGlobals
        if (r < AppGlobals.MIN_RATING || r > AppGlobals.MAX_RATING) {
            // If the rating is outside the valid range, throw an exception with a descriptive error message.
            throw new IllegalArgumentException(
                    ratingName + " rating must be between " + AppGlobals.MIN_RATING + " and " + AppGlobals.MAX_RATING);
        }
    }
}
