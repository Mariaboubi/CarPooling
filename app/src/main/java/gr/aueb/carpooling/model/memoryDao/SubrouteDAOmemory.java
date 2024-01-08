package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.SubrouteDAO;

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
