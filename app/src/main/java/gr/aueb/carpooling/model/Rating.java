package gr.aueb.carpooling.model;

public  class Rating implements RatingInterface {
    private final User user;
    private final Route route;
    private float politenessRating;

    public Rating(User user, Route route, float politenessRating) throws IllegalArgumentException {
        validatePolitenessRating(politenessRating);
        this.user = user;
        this.route = route;
        this.politenessRating = politenessRating;
    }

    public Route getRoute() {
        return route;
    }

    public User getUser() {
        return user;
    }

    public float getPolitenessRating() {
        return politenessRating;
    }

    public void setPolitenessRating(float politenessRating) throws IllegalArgumentException {
        validatePolitenessRating(politenessRating);
        this.politenessRating = politenessRating;
    }

    private void validatePolitenessRating(float politenessRating) throws IllegalArgumentException {
        if (politenessRating < AppGlobals.MIN_RATING || politenessRating > AppGlobals.MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between " + AppGlobals.MIN_RATING + " and " + AppGlobals.MAX_RATING);
        }
    }

}
