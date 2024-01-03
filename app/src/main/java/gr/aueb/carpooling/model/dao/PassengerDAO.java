package gr.aueb.carpooling.model.dao;

import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;

public interface PassengerDAO {
    void delete(Passenger entity);

    void delete(int id);

    void deleteAll();

    List<Passenger> findAll();

    void save(Passenger entity);

    Passenger find(String username);

    Passenger find(String username, String password);

    Passenger find(int id);

    boolean findPassenger(int id);

    Set<Route> findRoute(int id);

    int nextId();

}
