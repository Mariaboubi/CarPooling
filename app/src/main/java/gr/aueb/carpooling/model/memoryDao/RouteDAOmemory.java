package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Request_status;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.RouteDAO;

public class RouteDAOmemory implements RouteDAO {

    protected static ArrayList<Route> entities = new ArrayList<>();

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
    public Route findRouteByPassAndSub(Passenger pas, Subroute sub) {
        for (Route route : entities) {
            return route.getRoute(pas, sub);
        }
        return null;

    }

    @Override
    public ArrayList<Subroute> findSubroutesByPassenger(Passenger passenger) {
        ArrayList<Subroute> result = new ArrayList<>();
        for (Route route : entities) {
            HashMap<Passenger, Subroute> map = route.getPassengerRoutes();
            Set<Passenger> passengers = map.keySet();
            for (Passenger passenger1 : passengers) {
                if (passenger1 == passenger) {
                    result.add(map.get(passenger1));
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Subroute> findSubroutesByPassengerIsCompleted(Passenger passenger) {
        ArrayList<Subroute> subroutes = new ArrayList<>();
        for (Route route : entities) {
            HashMap<Passenger, Subroute> map = route.getPassengerRoutes();
            Set<Passenger> passengers = map.keySet();
            for (Passenger passenger1 : passengers) {
                if (passenger1 == passenger) {
                    subroutes.add(map.get(passenger1));
                }
            }
        }
        ArrayList<Subroute> result = new ArrayList<>();
        for(Subroute sub : subroutes){
            if(sub.getStatus()== Request_status.COMPLETED){
                result.add(sub);
            }
        }
        return result;
    }


    @Override
    public Route find(int id) {
        for (Route route : entities) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Route> findByDriver(Driver driver) {
        ArrayList<Route> result = new ArrayList<>();
        for (Route route : entities) {
            if (route.getDriver() == driver && !route.isCompleted()) {
                result.add(route);
            }
        }
        return  result;
    }
    public ArrayList<Route> findByDriverIsCompleted(Driver driver) {
        ArrayList<Route> result= new ArrayList<>();
        for(Route route : entities){
            if(route.getDriver()==driver  && route.isCompleted()){
                result.add(route);
            }
        }
        return result;
    }


    public Passenger findPassengerBySubroute(Subroute subroute) {
        HashMap<Passenger, Subroute> map;
        for (Route route1 : entities) {
            map = route1.getPassengerRoutes();
            Set<Passenger> passengers = map.keySet();
            for (Passenger passenger : passengers) {
                if (map.get(passenger) == subroute) {
                    return passenger;
                }
            }
        }
        return null;
    }

    @Override
    public Route findRouteBySubroute(Subroute subroute) {
        for (Route route : entities) {
            HashMap<Passenger, Subroute> map = route.getPassengerRoutes();
            Set<Passenger> passengers = map.keySet();
            for (Passenger passenger : passengers) {
                if (map.get(passenger) == subroute) {
                    return route;
                }
            }
        }
        return null;
    }
}
