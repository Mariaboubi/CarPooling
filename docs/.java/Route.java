package carPooling;

public class Route implements RouteInterface {
    private Driver driver;
    private List<Passenger> passengers;
    private RouteData routeData;
    private Money cost;

    public Route(Driver driver, List<Passenger> passengers, RouteData routeData) {
        this.driver = driver;
        this.passengers = passengers;
        this.routeData = routeData;
        this.cost = 0;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public RouteData getRouteData() {
        return routeData;
    }

    @Override
    public void setRouteData(RouteData routeData) {
        this.routeData = routeData;
    }

    @Override
    public Money calculateCost() {
        return this.cost;
    }
}