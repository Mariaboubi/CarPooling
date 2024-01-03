package gr.aueb.carpooling.model;

import gr.aueb.carpooling.model.contact.EmailAddress;
import java.util.*;

public class Driver extends User implements DriverInterface {
    private String iban, license_number, car_type; // personal details
    private final Set<Route> routes; // A HashSet of Routes that the driver takes part

    private final int driver_id;
    // Constructor
    public Driver(String username, String name, String surname, String phone, EmailAddress email,
                  String password, String age, String iban, String license_number, String car_type) {
        super(username, name, surname, phone, email, password, age);
        driver_id = super.getUserId();
        // Check for null values and provide meaningful error messages
        Objects.requireNonNull(iban, "IBAN cannot be null");
        Objects.requireNonNull(license_number, "License number cannot be null");
        Objects.requireNonNull(car_type, "Car type cannot be null");

        // Initialize the 'routes' set
        this.routes = new HashSet<>();

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

    public int getDriverId() {
        return driver_id;
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

    public void changeIban(String iban) throws NullPointerException {
        if (iban == null) {
            throw new NullPointerException("IBAN cannot be null");
        }
        this.iban = iban;
    }

    public void changeLicenseNumber(String license_num) throws NullPointerException{
        if (license_num == null) {
            throw new NullPointerException("License number cannot be null");
        }
        this.license_number = license_num;
    }

    public void changeCarType(String type) throws NullPointerException{
        if (type == null) {
            throw new NullPointerException("Car type cannot be null");
        }
        this.car_type = type;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public void removeRoute(Route route) throws UnsupportedOperationException {
        if(routes.size() > 0) {
            this.routes.remove(route);
        } else {
            throw new UnsupportedOperationException("Cannot remove from an empty route_data set.");
        }
    }

    public boolean hasRoute(Route route) {
        return routes.contains(route);
    }

    public HashSet<Route> getRoutes() {
        return new HashSet<>(routes); // Return a new set to avoid direct access to the internal set
    }

}
