package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

public interface SubrouteDAO {
    void delete(Subroute entity);

    void deleteAll();

    void save(Subroute entity);

    List<Subroute> findAll();

    Subroute find (int id);


    int nextId();
}
