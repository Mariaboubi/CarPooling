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

    private List<Route> routes;

    @Override
    public void delete(Subroute entity) {
        for (Route route : routes) {
            if (route.getPassengerRoutes().containsValue(entity)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    route.getPassengerRoutes().entrySet().removeIf(entry -> entry.getValue().equals(entity));
                }
            }
        }
    }


    @Override
    public void deleteAll() {routes.clear();}

    @Override
    public void save(Passenger passenger, Subroute entity) {

        for (Route route : routes) {
            if (route.getPassengers().contains(passenger)) {
                route.addPassenger(passenger, entity);
                break;
            }
        }
    }

    @Override
    public List<Subroute> findAll() {
        List<Subroute> allSubroutes = new ArrayList<>();
        for (Route route : routes) {
            allSubroutes.addAll(route.getPassengerRoutes().values());
        }
        return allSubroutes;
    }

    @Override
    public Subroute find(int id) {

        for (Route route : routes) {
            for (Subroute subroute : route.getPassengerRoutes().values()) {
                if (subroute.getId() == id) {
                    return subroute;
                }
            }
        }
        return null;
    }

    @Override
    public List<Subroute> findByPassenger(Passenger passenger) {
        List<Subroute> subroutesForPassenger = new ArrayList<>();
        for (Route route : routes) {
            Subroute subroute = route.getSubRouteByPassenger(passenger);
            if (subroute != null) {
                subroutesForPassenger.add(subroute);
            }
        }
        return subroutesForPassenger;
    }

    @Override
    public int nextId() {
        return (routes.size() > 0 ? routes.get(routes.size()-1).getId()+1 : 1);
    }
}
