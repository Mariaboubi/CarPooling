package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;

public interface DriverDAO {
    void delete(Driver entity);

    void delete(int id);

    void deleteAll();

    List<Driver> findAll();

    void save(Driver entity);
    boolean find(String username);
    Driver find(String username, String password);

    Driver find(int id);

    boolean findDriver(int id);

    int nextId();

}
