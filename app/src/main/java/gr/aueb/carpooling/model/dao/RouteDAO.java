package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

public interface RouteDAO {

    void delete(Route entity);

    void deleteAll();
    void save(Route entity);
    List<Route> findAll();

    Route find(int id);
    List<Route> findByDriver(Driver driver);
    int nextId();

    Passenger findPassenger(Route route, Subroute subroute);
    List<Route> findExistedRoutes();

    Route findByMap(Passenger pas, Subroute sub);

    Route findByDestDateDriver(String dest, String date,Driver driver);
}
