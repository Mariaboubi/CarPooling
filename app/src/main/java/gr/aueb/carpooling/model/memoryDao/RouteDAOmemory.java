package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.RouteDAO;

public class RouteDAOmemory implements RouteDAO {

    protected static ArrayList<Route> entities = new ArrayList<>();
    @Override
    public void delete(Route entity) {
        entities.remove(entity);
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(Route entity) {
        entities.add(entity);
    }

    @Override
    public List<Route> findAll() {
        return entities;
    }

    @Override
    public Route find(int id) {
        for(Route route: entities){
            if(route.getId()==id){
                return route;
            }
        }
        return null;
    }

    @Override
    public List<Route> findByCustomer(Driver driver) {
        ArrayList<Route> result= new ArrayList<>();
        for(Route route : entities){
            if(route.getDriver()==driver){
                result.add(route);
            }
        }
        return  result;
    }

    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
