
import java.util.HashSet;
import java.util.Set;


public class Driver extends User {
    private String iban, license_number, car_type; // tin=afm
    private Set<RouteData> route_data; // ArrayList with the orders that the Chef is working on currently

    public Driver(int id, String username, String name, String surname, String phone, EmailAddress email, String password, int age, int rate, String iban, String license_number, String car_type) {
        super(id, surname, name, surname, phone, email, password, age, rate); // calling the constructor of the father class(User)
        this.iban = iban;
        this.license_number = license_number;
        this.car_type = car_type;
        this.route_data = new HashSet<RouteData>();
    }

    public void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email, int age,
            String license_number, String car_type) {
        super.changePersonalDetails(username, name, surname, phone, email, age); // calling the father class method
        this.license_number = license_number;
        this.car_type = car_type;
    }

    // Getters
    public String getIban() {
        return iban;
    }

    public String getLicenseNumber() {
        return license_number;
    }
    public String getCarType() {
        return car_type;
    }

    public Set<RouteData> getRouteData() {
        return new HashSet<RouteData>(route_data);
    }

    Set<RouteData> friendRouteDatas() {
        return route_data;
    }

    public void changeIban(String iban) {
        this.iban = iban;
    }

    public void addRoute(RouteData newroute) {// called by the controller when a new order is being added in the restaurant
        route_data.add(newroute);
    }

    public boolean removeroute(RouteData route) {
        return route_data.remove(route); // returns True iff the order is in the list, else returns False
    }

}