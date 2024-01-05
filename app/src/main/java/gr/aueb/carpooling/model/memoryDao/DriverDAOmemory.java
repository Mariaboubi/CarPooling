package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverDAO;

public class DriverDAOmemory implements DriverDAO {

    protected static ArrayList<Driver> entities = new ArrayList<>();
    @Override
    public void delete(Driver entity) {
        entities.remove(entity);
    }

    @Override
    public void delete(int id) {
        for (Driver driver: entities){
            if (driver.getUserId()==id){
                entities.remove(driver);
                break;
            }
        }
    }

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(entities);
    }

    @Override
    public void save(Driver entity) {
        entities.add(entity);
    }

    @Override
    public boolean find(String username) {
        for(Driver driver: entities){
            if(username.equals(driver.getUsername())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Driver findByUsername(String username) {
        for(Driver driver: entities){
            if(username.equals(driver.getUsername())){
                return driver;
            }
        }
        return null;
    }

    @Override
    public Driver find(int id) {
        for(Driver driver: entities){
            if(driver.getUserId()==id){
                return driver;
            }
        }
        return null;
    }

    public boolean findDriver(int id) {
        for(Driver driver: entities){
            if(driver.getDriverId()==id){
                return true;
            }
        }
        return false;
    }


    @Override
    public int nextId() {
        return (UserDAOmemory.entities.size() > 0 ? UserDAOmemory.entities.get(UserDAOmemory.entities.size()-1).getUserId()+1 : 1);
    }
}
