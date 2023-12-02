import java.time.LocalDateTime;
import java.util.*;
// import java.time;

public class RouteData {
    private final int id;
    private LocalDateTime date;
    private Address destination;
    private float estimated_cost;
    private int number_of_passenger;
    private Driver driver;

    public RouteData(int id, LocalDateTime date,  Address destination, float cost, int num_pass, Driver driver ){
        this.id=id;
        this.date=date;
        //this.start_time=start_time;
        this.destination= destination;
        this.estimated_cost=cost;
        this.number_of_passenger= num_pass;
        this.driver=driver;
    }
    // Getter and Setter methods for id
    public int getId() {
        return this.id;
    }

    // Getter and Setter methods for date
    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Getter and Setter methods for start_time
    // public String getStart_time() {
    //     return this.start_time;
    // }

    // public void setStart_time(String start_time) {
    //     this.start_time = start_time;
    // }

    // Getter and Setter methods for destination
    public Address getDestination() {
        return this.destination == null ? null : new Address(this.destination);
    }

    public void setDestination(Address destination) {
        this.destination = destination == null ? null : new Address(destination);
    }

    // Getter and Setter methods for estimated_cost
    public float getEstimated_cost() {
        return this.estimated_cost;
    }

    public void setEstimated_cost(float estimated_cost) {
        this.estimated_cost = estimated_cost;
    }

    // Getter and Setter methods for number_of_passenger
    public int getNumber_of_passenger() {
        return this.number_of_passenger;
    }

    public void setNumber_of_passenger(int number_of_passenger) {
        this.number_of_passenger = number_of_passenger;
    }

    // Getter and Setter methods for driver
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        if (this.driver != null) {
            this.driver.friendRouteDatas().remove(this);
        }
        this.driver = driver;
        if (driver != null) {
            this.driver.friendRouteDatas().add(this);
        }
    }
    

}