package gr.aueb.carpooling.model.view.driver.DriverTopUp;

import gr.aueb.carpooling.model.view.View;

public interface DriverTopUpView extends View {

    void setBalance(String balance);

    String getDriverUername();

    void showErrorMessage(String title, String message);
}
