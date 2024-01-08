package gr.aueb.carpooling.model.view.driver.existed_routes;

import gr.aueb.carpooling.model.view.View;

public interface ExistedRouteView extends View {

    /**
     * Hide the recyclerView and display a visible message indicating the absence of routes
     */
    void ShowNoRoutes();

    /**
     * Display and set up the recyclerView, and hide the message indicating the absence of routes
     */
    void ShowRoutes();

    /**
     * Display an error message with information about route completion
     *
     * @param isCompleted The completion status message
     * @param s           Additional information for the error message
     */
    void showErrorMessage(String isCompleted, String s);
}
