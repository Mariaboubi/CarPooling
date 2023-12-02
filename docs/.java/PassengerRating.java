public class PassengerRating extends Rating implements PassengerRatingInterface {
    private float consistencyRating;
    private float reliabilityRating;


    public PassengerRating(Passenger passenger, Route route,
    float politenessRating, float consistencyRating, float reliabilityRating) {
        super(passenger, route, politenessRating);
        this.consistencyRating = consistencyRating;
        this.reliabilityRating = reliabilityRating;
    }

    @Override
    public void setConsistencyRating(float consistencyRating) throws IllegalArgumentException {
        if (consistencyRating < 0 && consistencyRating > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.consistencyRating = consistencyRating;
    }

    @Override
    public float getConsistencyRating() {
        return consistencyRating;
    }

    @Override
    public void setReliabilityRating(float reliabilityRating) throws IllegalArgumentException{
        if (reliabilityRating < 0 && reliabilityRating > 5){
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
        this.reliabilityRating = reliabilityRating;
    }

    @Override
    public float getReliabilityRating() {
        return reliabilityRating;
    }
    
}
