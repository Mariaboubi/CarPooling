package carPooling;

public class DriverRating implements DriverRatingInterface {
    private float securityRating;
    private float cleanlinessRating;

    public DriverRating(Driver driver, Route route,
            float securityRating, float politenessRating, float cleanlinessRating) {
        super(driver, route, politenessRating);
        this.securityRating = securityRating;
        this.cleanlinessRating = cleanlinessRating;
    }

    @Override
    public void setSecurityRating(float securityRating) throws IllegalArgumentException {
        if (securityRating < 0 && securityRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.securityRating = securityRating;
    }

    @Override
    public float getSecurityRating() {
        return securityRating;
    }

    @Override
    public void setCleanlinessRating(float cleanlinessRating) throws IllegalArgumentException {
        if (cleanlinessRating < 0 && cleanlinessRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.cleanlinessRating = cleanlinessRating;
    }

    @Override
    public float getCleanlinessRating() {
        return cleanlinessRating;
    }

}
