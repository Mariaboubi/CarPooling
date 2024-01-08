package gr.aueb.carpooling.model.view.driver.top_up;

import gr.aueb.carpooling.model.view.View;

public interface DriverTopUpView extends View {

    void setBalance(String balance);

    String getDriverUsername();

    void showErrorMessage(String title, String message);

    void openDriverFrontPageActivity(String username);
}
