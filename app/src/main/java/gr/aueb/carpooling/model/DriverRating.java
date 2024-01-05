package gr.aueb.carpooling.model;

import java.util.HashSet;
import java.util.Set;

public class DriverRating extends Rating implements DriverRatingInterface {
    private float securityRating; // Rating about security issues
    private float cleanlinessRating; // Rating about cleaning issues

    private final Set<Passenger> passangers;

    public DriverRating(Driver driver, Route route, float politenessRating,
                        float securityRating, float cleanlinessRating) {
        super(driver, route, politenessRating); // Assuming the base class constructor
        validateRating(cleanlinessRating, "Cleanliness");
        validateRating(securityRating, "Security");
        this.cleanlinessRating = cleanlinessRating;
        this.securityRating = securityRating;
        this.passangers = new HashSet<>();
    }

    @Override
    public void setSecurityRating(float securityRating) {
        validateRating(securityRating, "Security");
        this.securityRating = securityRating;
    }

    public void addPassenger(Passenger pas) {
        this.passangers.add(pas);
    }

    public void removePassenger(Passenger pas) throws UnsupportedOperationException {
        if(passangers.size() > 0) {
            this.passangers.remove(pas);
        } else {
            throw new UnsupportedOperationException("Cannot remove from an empty passenger rating set.");
        }
    }

    public HashSet<Passenger> getPassengers() {
        return new HashSet<>(passangers); // Return a new set to avoid direct access to the internal set
    }

    @Override
    public float getSecurityRating() {
        return securityRating;
    }

    public Route getRoute(){return super.getRoute();}

    @Override
    public void setCleanlinessRating(float cleanlinessRating) {
        validateRating(cleanlinessRating, "Cleanliness");
        this.cleanlinessRating = cleanlinessRating;
    }

    @Override
    public float getCleanlinessRating() {
        return cleanlinessRating;
    }

    @Override
    public float averageRating() {
        float sum = this.securityRating + this.getPolitenessRating() + this.cleanlinessRating;
        return (float) (sum / 3);
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
