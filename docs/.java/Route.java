package carPooling;

import java.util.List;

public interface Route {

    Driver getDriver();
    void setDriver(Driver driver);

    List<Passenger> getPassengers();
    void setPassengers(List<Passenger> passengers);

    RouteData getRouteData();
    void setRouteData(RouteData routeData);

    Money calculateCost();
}

