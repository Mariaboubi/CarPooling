package gr.aueb.carpooling.model.memoryDao;

import gr.aueb.carpooling.model.dao.Initializer;
import gr.aueb.carpooling.model.dao.UserDAO;

public class MemoryInitialized extends Initializer {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOmemory();
    }
}
