package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;

public class DriverRatingDAOmemory implements DriverRatingDAO {

    protected static ArrayList<DriverRating> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(DriverRating entity) {
        entities.add(entity);
    }

    @Override
    public ArrayList<DriverRating> findAll() {
        return entities;
    }
}
