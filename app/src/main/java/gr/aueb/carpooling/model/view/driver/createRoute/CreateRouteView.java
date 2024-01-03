package gr.aueb.carpooling.model.view.driver.createRoute;

import java.util.HashMap;

import gr.aueb.carpooling.model.view.View;

public interface CreateRouteView extends View {
    HashMap<String,String> getRouteDetails();

    void showErrorMessage(String title, String message);

    void goBack();

    void showRouteAddedMessage();
}
