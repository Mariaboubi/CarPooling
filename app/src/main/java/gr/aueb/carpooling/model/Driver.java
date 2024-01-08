package gr.aueb.carpooling.model;

import gr.aueb.carpooling.model.contact.EmailAddress;
import java.util.*;

public class Driver extends User implements DriverInterface {
    private String iban, license_number, car_type; // personal details
    private final Set<Route> routes; // A HashSet of Routes that the driver takes part

    private final Set<DriverRating> rates;

    private final int driver_id;
    private final DriverRating driver_rating;
    // Constructor
    public Driver(String username, String name, String surname, String phone, EmailAddress email,
                  String password, String age, String iban, String license_number, String car_type) {
        super(username, name, surname, phone, email, password, age);
        driver_id = super.getUserId();
        driver_rating = (DriverRating) super.getRate();

        // Initialize the 'routes' set
        this.routes = new HashSet<>();
        this.rates = new HashSet<>();

        // Initialize the instance variables
        this.iban = iban;
        this.license_number = license_number;
        this.car_type = car_type;
    }

    public void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email,
                                      String age, String license_number, String car_type) {
        super.changePersonalDetails(username, name, surname, phone, email, age); // calling the father class method
        this.license_number = license_number;
        this.car_type = car_type;
    }


    public String getIban() {
        return iban;
    }

    public String getLicenseNumber() {
        return license_number;
    }

    public String getCarType() {
        return car_type;
    }

    public void changeIban(String iban)  {
        this.iban = iban;
    }

    public void changeLicenseNumber(String license_num) {
        this.license_number = license_num;
    }

    public void changeCarType(String type) {
        this.car_type = type;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void addRates(DriverRating rating) {
        this.rates.add(rating);
    }

    public float averageRating(){
        int size= rates.size();
        float sum=0;
        for(DriverRating rates: rates){
            sum+= rates.averageRating();
        }
        return rates.size() != 0 ? (float) (sum / size) : 0;
    }


    public void removeRoute(Route route)  {
        if(routes.size() > 0) {
            this.routes.remove(route);
        }
    }


    public boolean hasRoute(Route route) {
        return routes.contains(route);
    }

    public boolean hasRate(DriverRating rate) {
        return rates.contains(rate);
    }

    public HashSet<Route> getRoutes() {
        return new HashSet<>(routes); // Return a new set to avoid direct access to the internal set
    }

    public HashSet<DriverRating> getRates() {
        return new HashSet<>(rates); // Return a new set to avoid direct access to the internal set
    }

}
