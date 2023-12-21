package gr.aueb.carpooling.model.view.LogIn;

import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.dao.UserDAO;

public class LogInPresenter {
    private LogInView view;
    private UserDAO userDAO;

    private String inputUsername;
    private String inputPassword;
    public LogInPresenter(UserDAO userDAO){
        this.userDAO=userDAO;
    }
    public LogInView getView() {
        return view;
    }
    public void setView(LogInView view) {
        this.view = view;
    }

    public void authenticate() {
        inputUsername = view.ExtractUsername();
        inputPassword = view.ExtractPassword();
        User user= userDAO.find(inputUsername,inputPassword);

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία.");
        }else if(user!=null){
            view.showUserFoundMessage(user.getUserId());

        }else{
            view.showErrorMessage("Λάθος στοιχεία", "Τα στοιχεία που εισάγατε δεν ήταν σωστά. Προσπαθήστε ξανά");
        }
    }
}
