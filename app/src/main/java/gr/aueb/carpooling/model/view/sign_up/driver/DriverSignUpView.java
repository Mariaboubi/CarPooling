package gr.aueb.carpooling.model.view.sign_up.driver;

import gr.aueb.carpooling.model.view.View;

public interface DriverSignUpView extends View {
    String getDriverLicense();

    String getCarType();

    String getIban();

    void showErrorMessage(String title, String message);

    void showRegistrationSuccessMessage(String title,String message);

}
