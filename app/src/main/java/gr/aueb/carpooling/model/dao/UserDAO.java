package gr.aueb.carpooling.model.dao;

import java.util.List;

import gr.aueb.carpooling.model.User;

public interface UserDAO {
//    void delete(User entity);
//
//    void delete(int id);

    void deleteAll();

//    List<User> findAll();
//
    void save(User entity);
//
    User findByUsername(String username, String password);
//
    User findByUsername(String username);

    //debug
   int size();
}
