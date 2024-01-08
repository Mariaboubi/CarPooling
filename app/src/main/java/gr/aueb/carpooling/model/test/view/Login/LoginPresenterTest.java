package gr.aueb.carpooling.model.test.view.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionPresenter;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionView;
import gr.aueb.carpooling.model.view.log_in.LogInPresenter;
import gr.aueb.carpooling.model.view.log_in.LogInView;

public class LoginPresenterTest {
    private UserDAO userDAO;

    private LogInViewStub view;
    private LogInPresenter presenter;

    private User user;
    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOmemory();
        presenter = new LogInPresenter(userDAO);
        presenter.setView(view);
        //ogInView testView = new LogInViewStub();

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


    }

    @Test
    public void setView() {
        LogInView testView = new LogInViewStub();
        presenter.setView(testView);
        assertEquals(presenter.getView(),testView);

    }
    @Test
    public  void Authenticate(){
        view.setUsername("maria123");
        view.setPassword("12345678");
        presenter.authenticate();
    }
}
