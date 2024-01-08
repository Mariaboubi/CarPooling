package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.dao.DriverDAO;

public class DriverDAOmemory implements DriverDAO {

    protected static ArrayList<Driver> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
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

    public ArrayList<Driver> findAll() {
        return entities;
    }
}
