package gr.aueb.carpooling.model.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;

public interface RouteDAO {

    void delete(Route entity);

    void deleteAll();
    void save(Route entity);
    List<Route> findAll();

    //    public ArrayList<Subroute> findSubroutesByPassanger(Passenger passenger) {
//        ArrayList<Subroute> result= new ArrayList<>();
//        for(Route route: entities){
//            result.add(route.getSubRouteByPassenger(passenger));
//
//        }
//        if (result.isEmpty()){
//            return null;
//        }else{
//            return result;
//        }
//
//    }
    //ArrayList<Subroute> findSubrouteByPassenger(Passenger passenger);

    Route find(int id);
    ArrayList<Route> findByDriver(Driver driver);
    int nextId();

    Passenger findPassenger(Route route, Subroute subroute);

    Passenger findPassengerByRoute(Route route);
    ArrayList<Route> findExistedRoutes();

    Route findByMap(Passenger pas, Subroute sub);

    Route findByDestDateDriver(String dest, String date,Driver driver);

    ArrayList<Subroute> findSubroutesByPassanger(Passenger passenger);
}
