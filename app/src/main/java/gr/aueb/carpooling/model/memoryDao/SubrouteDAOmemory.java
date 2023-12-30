package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.SubrouteInterface;
import gr.aueb.carpooling.model.dao.SubrouteDAO;

public class SubrouteDAOmemory implements SubrouteDAO {

    protected static ArrayList<Subroute> entities = new ArrayList<>();

    @Override
    public void delete(Subroute entity) {entities.remove(entity);}

    @Override
    public void deleteAll() {entities.clear();}

    @Override
    public void save(Subroute entity) {entities.add(entity);}

    @Override
    public List<Subroute> findAll() {
        ArrayList<Subroute> result= new ArrayList<>();
        result.addAll(entities);
        return result;
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

//    @Override
//    public List<Subroute> findByPassenger(Passenger passenger) {
//        ArrayList<Subroute> result= new ArrayList<>();
//        for(Subroute subroute : entities){
//            if(subroute .getPassenger()==passenger){
//                result.add(subroute);
//            }
//        }
//        return  result;
//    }

    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
