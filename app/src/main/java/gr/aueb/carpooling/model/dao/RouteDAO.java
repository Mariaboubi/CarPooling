package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

public interface RouteDAO {


    void deleteAll();
    void save(Route entity);
    List<Route> findAll();

    Route find(int id);
    ArrayList<Route> findByDriver(Driver driver);


    Route findRouteByPassAndSub(Passenger pas, Subroute sub);


    ArrayList<Route> findByDriverIsCompleted(Driver driver);

    ArrayList<Subroute> findSubroutesByPassenger(Passenger passenger);

    ArrayList<Subroute> findSubroutesByPassengerIsCompleted(Passenger passenger);

    Passenger findPassengerBySubroute(Subroute currentItem);

    Route findRouteBySubroute(Subroute subroute);
}
