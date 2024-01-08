package gr.aueb.carpooling.model;

public class PassengerRating extends Rating implements PassengerRatingInterface{
    private String consistencyRating; // Rating for consistency issues
    private  String reliabilityRating; // Rating for reliability issues

    private final Passenger passenger;

    // Constructor
    public PassengerRating(Passenger passenger, Route route, String politenessRating,
                           String consistencyRating, String reliabilityRating)  {
        super(passenger, route, politenessRating);
        this.consistencyRating = consistencyRating;
        this.reliabilityRating = reliabilityRating;
        this.passenger=passenger;
    }

    public Passenger getPassenger(){
        return passenger;
    }

    public void setConsistencyRating(String consistencyRating)  {
        this.consistencyRating = consistencyRating;
    }

    public String getConsistencyRating() {
        return consistencyRating;
    }

    public void setReliabilityRating(String reliabilityRating)  {
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

}
