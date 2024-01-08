package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;

public interface PassengerDAO {

    void deleteAll();

    List<Passenger> findAll();

    void save(Passenger entity);

    boolean find(String username);


    Passenger findByUsername(String username);


    ArrayList<Passenger> findAllByRoute(Route route);

    Passenger find(int id);

    boolean findPassenger(int id);


}
