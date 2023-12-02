public class Rating {
    private User user;
    private Route route;
    private float politenessRating;

    public Rating(User user, Route route, float politenessRating) {
        this.user = user;
        this.route = route;
        this.politenessRating = politenessRating;
    }

    // Getters and setters for common attributes

    public float getPolitenessRating() {
        return politenessRating;
    }

    public void setPolitenessRating(float politenessRating) throws IllegalArgumentException {
        if (politenessRating < 0 || politenessRating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.politenessRating = politenessRating;
    }
}
