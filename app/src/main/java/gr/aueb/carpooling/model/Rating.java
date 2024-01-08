package gr.aueb.carpooling.model;

public  class Rating implements RatingInterface {
    private final User user;
    private final Route route;
    private String politenessRating;

    public Rating(User user, Route route, String politenessRating)  {
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

    public void setPolitenessRating(String politenessRating)  {
        this.politenessRating = politenessRating;
    }

}
