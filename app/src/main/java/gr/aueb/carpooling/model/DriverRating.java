package gr.aueb.carpooling.model;

import java.util.HashSet;
import java.util.Set;

public class DriverRating extends Rating implements DriverRatingInterface {
    private String securityRating; // Rating about security issues
    private String cleanlinessRating; // Rating about cleaning issues

    private final Set<Passenger> passengers;

    public DriverRating(Driver driver, Route route, String politenessRating,
                        String securityRating, String cleanlinessRating) {
        super(driver, route, politenessRating); // Assuming the base class constructor
//        validateRating(cleanlinessRating, "Cleanliness");
//        validateRating(securityRating, "Security");
        this.cleanlinessRating = cleanlinessRating;
        this.securityRating = securityRating;
        this.passengers = new HashSet<>();
    }

    @Override
    public void setSecurityRating(String securityRating) {
        //validateRating(securityRating, "Security");
        this.securityRating = securityRating;
    }

    public void addPassenger(Passenger pas) {
        this.passengers.add(pas);
    }

    public void removePassenger(Passenger pas) throws UnsupportedOperationException {
        if(passengers.size() > 0) {
            this.passengers.remove(pas);
        } else {
            throw new UnsupportedOperationException("Cannot remove from an empty passenger rating set.");
        }
    }

    public HashSet<Passenger> getPassengers() {
        return new HashSet<>(passengers); // Return a new set to avoid direct access to the internal set
    }

    @Override
    public String getSecurityRating() {
        return securityRating;
    }

    public Route getRoute(){return super.getRoute();}

    @Override
    public void setCleanlinessRating(String cleanlinessRating) {
        //validateRating(cleanlinessRating, "Cleanliness");
        this.cleanlinessRating = cleanlinessRating;
    }

    @Override
    public String getCleanlinessRating() {
        return cleanlinessRating;
    }

    @Override
    public float averageRating() {
        float sec_rating = Float.parseFloat(this.securityRating);
        float pol_rating = Float.parseFloat(this.getPolitenessRating());
        float clean_rating = Float.parseFloat(this.cleanlinessRating);
        float sum = sec_rating + pol_rating + clean_rating;
        return (float) (sum / 3);
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
