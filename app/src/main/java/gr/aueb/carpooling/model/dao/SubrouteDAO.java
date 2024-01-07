package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.Subroute;

public interface SubrouteDAO {
    void delete(Subroute entity);

    void deleteAll();

    void save(Subroute entity);

    List<Subroute> findAll();


    Subroute findById(int id);




    int nextId();
}
