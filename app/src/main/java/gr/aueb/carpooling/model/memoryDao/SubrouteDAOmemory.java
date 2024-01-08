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



    public Subroute findById(int id) {
            for(Subroute subroute: entities){
                if(subroute.getId()==id){
                    return subroute;
                }
            }
            return null;
    }

    @Override
    public ArrayList<Subroute> findAll() {
        return entities;
    }

}
