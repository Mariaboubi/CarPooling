package gr.aueb.carpooling.model.memoryDao;

import java.util.ArrayList;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.dao.UserDAO;

public class UserDAOmemory implements UserDAO {

    protected static ArrayList<User> entities = new ArrayList<>();

    @Override
    public void deleteAll() {
        entities.clear();
    }

    @Override
    public void save(User entity) {
        entities.add(entity);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for(User user: entities){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByUsername(String username)
    {
        for(User user: entities){
            if(username.equals(user.getUsername())){
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return entities;
    }

}
