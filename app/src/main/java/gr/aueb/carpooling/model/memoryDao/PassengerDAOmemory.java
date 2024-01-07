package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerDAO;

public class PassengerDAOmemory implements PassengerDAO {

    protected static ArrayList<Passenger> entities = new ArrayList<>();
    @Override
    public void delete(Passenger entity) {entities.remove(entity);}

    @Override
    public void delete(int id) {
        for (Passenger passenger: entities){
            if (passenger.getUserId()==id){
                entities.remove(passenger);
                break;
            }
        }
    }

    @Override
    public void deleteAll() {entities.clear();}

    @Override
    public List<Passenger> findAll() {return new ArrayList<>(entities);}

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

    @Override
    public Passenger find(String username, String password) {
        for(Passenger passenger: entities){
            if(username.equals(passenger.getUsername()) && password.equals(passenger.getPassword())){
                return passenger;
            }
        }
        return null;
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
    public Passenger findByName(String name) {
        for(Passenger passenger: entities){
            if(name.equals(passenger.getName())){
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


    @Override
    public Passenger find(int id) {
        for(Passenger passenger: entities){
            if(passenger.getUserId()==id){
                return passenger;
            }
        }
        return null;
    }

    public boolean findPassenger(int id) {
        for(Passenger passenger: entities){
            if(passenger.getPassengerId()==id){
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<Route> findRoute(int id) {
        ArrayList<Route> result= new ArrayList<>();
        for(Passenger passenger : entities){
            if(passenger.getUserId()==id){
                result.addAll(passenger.getRoutes());
            }
        }
        return (Set<Route>) result;
    }

    @Override
    public int nextId() {
        return (UserDAOmemory.entities.size() > 0 ? UserDAOmemory.entities.get(UserDAOmemory.entities.size()-1).getUserId()+1 : 1);
    }
}
