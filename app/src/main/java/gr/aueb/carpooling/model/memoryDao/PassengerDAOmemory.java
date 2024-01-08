package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerDAO;

public class PassengerDAOmemory implements PassengerDAO {

    protected static ArrayList<Passenger> entities = new ArrayList<>();

    @Override
    public void deleteAll() {entities.clear();}


    @Override
    public void save(Passenger entity) {entities.add(entity);}

    @Override
    public boolean find(String username) {
        for(Passenger passenger: entities){
            if(username.equals(passenger.getUsername())){
                return true;
            }
        }
        return false;
    }

    public Passenger findByUsername(String username) {
        for(Passenger passenger: entities){
            if(username.equals(passenger.getUsername())){
                return passenger;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Passenger> findAllByRoute(Route route) {
        ArrayList<Passenger> result= new ArrayList<>();
        for(Passenger passenger : entities){
            if(passenger.getRoutes().contains(route)){
                result.add(passenger);
            }
        }
        return  result;
    }



    public ArrayList<Passenger> findAll() {
        return entities;
    }

}
