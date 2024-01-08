package gr.aueb.carpooling.model.test.view.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.dao.UserDAO;
import gr.aueb.carpooling.model.memoryDao.UserDAOmemory;
import gr.aueb.carpooling.model.view.log_in.LogInPresenter;
import gr.aueb.carpooling.model.view.log_in.LogInView;

public class LoginPresenterTest {
    private UserDAO userDAO;

    private LogInViewStub view;
    private LogInPresenter presenter;

    private User user;

    @BeforeEach
    public void setUp() {
        view = new LogInViewStub();
        userDAO = new UserDAOmemory();
        presenter = new LogInPresenter(userDAO);
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
    }

    @Test
    public void Authenticate() {
        view.setUsername("maria123");
        view.setPassword("12345678");
        presenter.authenticate();
    }

    public static class LogInViewStub implements LogInView {
        String username, password, errorTitle, errorMessage;

        public LogInViewStub() {
            username = password = errorTitle = errorMessage = "";
        }

        @Override
        public String extractUsername() {
            return username;
        }

        @Override
        public String extractPassword() {
            return password;
        }

        public void setUsername(String value) {
            username = value;
        }

        public void setPassword(String value) {
            password = value;
        }

        @Override
        public void openSignupActivity() {

        }

        @Override
        public void showErrorMessage(String title, String message) {
            errorTitle = title;
            errorMessage = message;
        }

        @Override
        public void openAttributeSelectionActivity(String username) {

        }
    }
}
