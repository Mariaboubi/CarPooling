package gr.aueb.carpooling.model.test.view.AttributeSelection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionPresenter;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionView;

public class AttributeSelectionPresenterTest {

    private AttributeSelectionPresenter presenter;

    private  PassengerDAO passengerDAO;

    private AttributeSelectionView view;

    private  PassengerDAOmemory passengerDAOmemory;

    private  DriverDAOmemory driverDAOmemory;
    private DriverDAO driverDAO;

    private UserDAO userDAO;

    private User user;

    private Driver driver;

    private Passenger passenger;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOmemory();
        driverDAO = new DriverDAOmemory();
        passengerDAO = new PassengerDAOmemory();
        presenter=new AttributeSelectionPresenter(driverDAOmemory,passengerDAOmemory);
        presenter.setView(view);

        user = new User(
                "maria123",
                "maria",
                "pappa",
                "6900000000",
                new EmailAddress("pappas@gmail.com"),
                "12345678",
                "25"
        );
        userDAO.save(user);

        driver = new Driver(user.getUsername(), user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.getAge(), "GRE10230910290194", "112233", "mersedes");
        driverDAO.save(driver);

        User user2 = new User(
                "markos_andre",
                "markos",
                "andreopoulos",
                "6972169794",
                new EmailAddress("markos@gmail.com"),
                "11111111",
                "27");
        userDAO.save(user2);

        passenger = new Passenger(user2.getUsername(), user2.getName(), user2.getSurname(), user2.getPhone(), user2.getEmail(), user2.getPassword(), user2.getAge(), "10230910290333", "Markos kapelas", "481");
        passengerDAO.save(passenger);

    }

    @Test
    public void setView() {
        AttributeSelectionView testView = new AttributeSelectionView() {
            @Override
            public void showErrorMessage(String title, String message) {

            }
        };
        presenter.setView(testView);
        assertEquals(presenter.getView(),testView);
    }
    @Test
    public void AuthenticPassengerTest(){
        assertTrue(presenter.authenticateAttributePassenger("markos_andre"));
    }
//    @Test
//    public void AuthenticDriverTest(){
//        assertTrue(presenter.authenticateAttributePassenger("maria123"));
//    }


}
