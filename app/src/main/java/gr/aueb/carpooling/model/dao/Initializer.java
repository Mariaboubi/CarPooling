package gr.aueb.carpooling.model.dao;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;

public abstract class Initializer {
    public void eraseAll() {
        UserDAO userDAO = getUserDAO();
        userDAO.deleteAll();
    }
    public void prepareData() {
        eraseAll();
        UserDAO userDAO = getUserDAO();
        EmailAddress email1 = new EmailAddress("pappas@gmail.com");
        EmailAddress email2 = new EmailAddress("markos@gmail.com");
        EmailAddress email3 = new EmailAddress("kostas_03@gmail.com");
        User user1= new User("maria123","maria","pappa","6900000000",email1,"1234",25);
        User user2= new User("markos_andre","markos","andreopoulos","6972169794",email2,"1111",27);
        User user3= new User("kostas3","kostas","Papadopoulos","6972169794",email3,"2003",20);
        userDAO.save(user1);
        userDAO.save(user2);
        userDAO.save(user3);
    }
    public abstract UserDAO getUserDAO();
}
