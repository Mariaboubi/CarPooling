package gr.aueb.carpooling.model.view.driver.create_route;

import gr.aueb.carpooling.model.view.View;

public interface CreateRouteView extends View {

    String Streeet();

    String Number();

    String City();

    String ZipCode();

    String EstimatedCost();

    String MaxPassengers();
    String Date();
    void showErrorMessage(String title, String message);

    void showRouteAddedMessage();
}
