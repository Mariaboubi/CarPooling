package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.User;

public interface UserDAO {
    void delete(User entity);

    void delete(int id);

    void deleteAll();

    List<User> findAll();

    void save(User entity);

    User find(String username, String password);

    User find(String username);

    User find(int id);

    //debug
    int size();
}
