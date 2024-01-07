package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
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

    public List<Route> findExistedRoutes() {
        ArrayList<Route> result= new ArrayList<>();
        for(Route route: entities){
            if(!route.isCompleted()){
                result.add(route);
                return result;
            }
        }
        return null;
    }

    @Override
    public Route findByMap(Passenger pas, Subroute sub) {
        for(Route route: entities){
            return route.getRoute(pas,sub);
        }
        return null;

    }

    public Route findByDestDateDriver(String dest,String date,Driver driver) {
        for(Route route: entities){
            if(route.getDestination().toString().equals(dest)  && route.getDate().toString().equals(date) && route.getDriver().equals(driver)){
                return route;
            }
        }
        return null;
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
    public List<Route> findByDriver(Driver driver) {
        ArrayList<Route> result= new ArrayList<>();
        for(Route route : entities){
            if(route.getDriver()==driver && !route.isCompleted()){
                result.add(route);
            }
        }
        return  result;
    }

    public Passenger findPassenger(Route route, Subroute subroute) {
        HashMap<Passenger, Subroute> map ;
        for(Route route1 : entities){
            if (route1 == route) {
                map = route.getPassengerRoutes();
            }

        }
//        for()
        return null;
    }
    @Override
    public int nextId() {
        return (entities.size() > 0 ? entities.get(entities.size()-1).getId()+1 : 1);
    }
}
