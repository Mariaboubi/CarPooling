package gr.aueb.carpooling.model.test.view.Login;

import gr.aueb.carpooling.model.view.log_in.LogInView;

public class LogInViewStub implements LogInView {
    String username,password,errorTitle,errorMessage;

    public LogInViewStub()
    {
        username = password  = errorTitle = errorMessage = "";
    }
    @Override
    public String extractUsername() {
        return username;
    }

    @Override
    public String extractPassword() {
        return password;
    }
    public void setUsername(String value)
    {
        username = value;
    }

    public void setPassword(String value)
    {
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
