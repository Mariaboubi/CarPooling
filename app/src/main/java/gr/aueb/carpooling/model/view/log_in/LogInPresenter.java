package gr.aueb.carpooling.model.view.log_in;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.dao.UserDAO;

public class LogInPresenter {
    private LogInView view;
    private final UserDAO userDAO;

    public LogInPresenter(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LogInView getView() {
        return view;
    }

    public void setView(LogInView view) {
        this.view = view;
    }

    public void authenticate() {
        String inputUsername = view.extractUsername();
        String inputPassword = view.extractPassword();
        User user = userDAO.findByUsername(inputUsername, inputPassword);

        if (inputUsername.isEmpty() && inputPassword.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields");
        } else if (user != null) {
            view.openAttributeSelectionActivity(inputUsername);
        } else {
            view.showErrorMessage("Incorrect username or password.", "Try again!");
        }
    }

    public void onSignup() {
        view.openSignupActivity();
    }
}
