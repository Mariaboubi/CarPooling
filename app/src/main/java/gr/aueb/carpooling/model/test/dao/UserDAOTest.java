package gr.aueb.carpooling.model.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;

public class UserDAOTest {
    private UserDAO userDAO;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOmemory();

        user1 = new User("markos_andre", "markos", "andreopoulos", "6972169794", new EmailAddress("markos@gmail.com"), "11111111", "27");
        user2 = new User("maria123", "maria", "pappa", "1234567890", new EmailAddress("maria@gmail.com"), "12345678", "30");

        userDAO.save(user1);
        userDAO.save(user2);
    }

    @AfterEach
    public void tearDown() {
        userDAO.deleteAll();
    }

    @Test
    public void testFindByUsernameAndPassword() {
        User foundUser1 = userDAO.findByUsernameAndPassword(user1.getUsername(), user1.getPassword());
        assertNotNull(foundUser1);
        assertEquals(user1.getUsername(), foundUser1.getUsername());

        User foundUser2 = userDAO.findByUsernameAndPassword(user2.getUsername(), user2.getPassword());
        assertNotNull(foundUser2);
        assertEquals(user2.getUsername(), foundUser2.getUsername());

    }

    @Test
    public void testFindByUsernameAndPasswordNonExistent() {
        User foundUser = userDAO.findByUsernameAndPassword("nonexistent", "password");
        assertNull(foundUser);
    }

    @Test
    public void testFindByUsername() {
        User foundUser1 = userDAO.findByUsername(user1.getUsername());
        User foundUser2 = userDAO.findByUsername(user2.getUsername());

        assertNotNull(foundUser1);
        assertNotNull(foundUser2);

        assertEquals(user1, foundUser1);
        assertEquals(user2, foundUser2);
    }

    @Test
    public void testFindByUsernameNonExistent() {
        User foundUser = userDAO.findByUsername("nonexistent");
        assertNull(foundUser);
    }

    @Test
    public void testFindAll() {
        ArrayList<User> allUsers = userDAO.findAll();
        assertTrue(allUsers.contains(user1));
        assertTrue(allUsers.contains(user2));
    }
}
