package gr.aueb.carpooling.model.memoryDao;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;

public class SubrouteDAOmemory implements SubrouteDAO {

    protected static ArrayList<Subroute> entities = new ArrayList<>();
    @Override
    public void delete(Subroute entity) {
        entities.remove(entity);
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(Subroute entity) {
        entities.add(entity);
    }

    @Override
    public List<Subroute> findAll() {
        return entities;
    }

    @Override
    public Subroute find(int id) {
        for(Subroute subroute: entities){
            if(subroute.getId()==id){
                return subroute;
            }
        }
        return null;
    }



    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
