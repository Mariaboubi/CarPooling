package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;

public interface RouteDAO {

    void delete(Route entity);

    void deleteAll();
    void save(Route entity);
    List<Route> findAll();

    Route find(int id);
    List<Route> findByDriver(Driver driver);
    int nextId();

    List<Route> findExistedRoutes();
}
