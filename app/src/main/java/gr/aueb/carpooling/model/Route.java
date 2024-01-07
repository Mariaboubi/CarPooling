package gr.aueb.carpooling.model;

import org.threeten.bp.LocalDateTime;

import java.util.*;

import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;

public class Route implements RouteInterface {
    private static int route_id = 0; // Id of All the Route Objects
    private final int id; // Id of the Route
    private Driver driver; // The driver of the Route
    private final HashMap<Passenger, Subroute> passenger_routes; // A HashMap of the Passengers of the Route and their SubRoute
    private int max_passengers; // Number of max passengers of the Route
    private Money estimated_cost; // An estimated cost of the Route
    private LocalDateTime date; // Route's date
    private Address destination; // Route's destination
    private boolean completed; // Route's state, completed or not
    private final Currency euroCurrency = Currency.getInstance("EUR");
    public Money total_cost; // Total cost of the Route

    public Route(Driver driver, Money estimated_cost, LocalDateTime date, Address destination, int max_passengers,
                 boolean completed) {
        this.id = ++ route_id;
        this.driver = driver;
        this.date = date;
        this.passenger_routes = new HashMap<>();
        this.estimated_cost = estimated_cost;
        this.destination = destination;
        this.max_passengers = max_passengers;
        this.completed = false;
        this.total_cost = null;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void Completed() {
        this.completed = true;
    }

    public int getId() {
        return this.id;
    }

    public int getMaxPassengers() {
        return this.max_passengers;
    }

    public void setMaxPassengers(int pass) {
        this.max_passengers = pass;
    }

    public Driver getDriver() {
        return this.driver;
    }


    public Money getTotalCost() {
        return this.total_cost;
    }


    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public HashMap<Passenger, Subroute> getPassengerRoutes() {
        return this.passenger_routes;
    }


    public Set<Passenger> getPassengers() {
        return this.passenger_routes.keySet();
    }


    public void addPassenger(Passenger passenger, Subroute subroute) {
        this.passenger_routes.put(passenger, subroute);
    }

    public Route getRoute(Passenger passenger, Subroute subroute) {
        if (passenger_routes.containsKey(passenger) && Objects.equals(passenger_routes.get(passenger), subroute)) {
            return this;
        }
        return null;
    }

    public Subroute getSubRouteByPassenger(Passenger passenger) {
        return this.passenger_routes.get(passenger);
    }

    public void removePassenger(Passenger passenger) {
        passenger_routes.remove(passenger);
    }

    public Money getPassengerCost(Passenger passenger) {
        return this.passenger_routes.get(passenger).calculateCost();
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Address getDestination() {
        return this.destination;
    }

    public String getDestinationString() {
        return this.destination.toString();
    }

    public void setDestination(Address destination) {
        this.destination = destination;
    }
    public Money getEstimatedCost() {
        return this.estimated_cost;
    }
    public void setEstimatedCost(Money estimated_cost) {
        this.estimated_cost = estimated_cost;
    }
    public void calculateTotalCost() {
        Money total_cost = new Money(0.0, euroCurrency);
        for (Subroute sub_route : this.passenger_routes.values()) {
            total_cost = total_cost.plus(sub_route.calculateCost());
        }
        this.total_cost = total_cost;
    }
}
