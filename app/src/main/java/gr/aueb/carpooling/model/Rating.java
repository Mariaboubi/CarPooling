package gr.aueb.carpooling.model;

public  class Rating implements RatingInterface {
    private final User user;
    private final Route route;
    private String politenessRating;

    public Rating(User user, Route route, String politenessRating) throws IllegalArgumentException {
        //validatePolitenessRating(politenessRating);
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

    public String getPolitenessRating() {
        return politenessRating;
    }

    public void setPolitenessRating(String politenessRating) throws IllegalArgumentException {
        //validatePolitenessRating(politenessRating);
        this.politenessRating = politenessRating;
    }

    private void validatePolitenessRating(String politenessRating) throws IllegalArgumentException {
        float r = Float.parseFloat(politenessRating);
        if (r < AppGlobals.MIN_RATING || r > AppGlobals.MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between " + AppGlobals.MIN_RATING + " and " + AppGlobals.MAX_RATING);
        }
    }

}
