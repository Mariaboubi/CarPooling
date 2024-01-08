package gr.aueb.carpooling.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DriverRating extends Rating implements DriverRatingInterface {
    private String securityRating; // Rating about security issues
    private String cleanlinessRating; // Rating about cleaning issues

    private Driver driver;

    private final HashMap<Passenger,DriverRating> passengers_has_rate;

    public DriverRating(Driver driver, Route route, String politenessRating,
                        String securityRating, String cleanlinessRating) {
        super(driver, route, politenessRating); // Assuming the base class constructor
        this.cleanlinessRating = cleanlinessRating;
        this.securityRating = securityRating;
        this.passengers_has_rate = new HashMap<>();
        this.driver=driver;
    }
    public Driver getDriver(){
        return this.driver;
    }
    @Override
    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }

    public void addRate(Passenger pas,DriverRating rate) {
        this.passengers_has_rate.put(pas, rate);
    }



    public HashMap<Passenger,DriverRating> getPassengersRates() {
        return new HashMap<>(passengers_has_rate); // Return a new set to avoid direct access to the internal set
    }

    @Override
    public String getSecurityRating() {
        return securityRating;
    }

    public Route getRoute(){return super.getRoute();}

    @Override
    public void setCleanlinessRating(String cleanlinessRating) {
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

    public boolean hasRate(DriverRating rate) {
        return passengers_has_rate.containsValue(rate);
    }

}
