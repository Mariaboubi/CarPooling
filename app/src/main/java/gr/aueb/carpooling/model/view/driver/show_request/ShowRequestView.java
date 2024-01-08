package gr.aueb.carpooling.model.view.driver.show_request;

import gr.aueb.carpooling.model.view.View;

public interface ShowRequestView  extends View {

    /**
     * Hide the recyclerView and display a visible message indicating the absence of requests
     */
    void ShowNoRequests();


    /**
     * Display and set up the recyclerView, and hide the message indicating the absence of requests
     */
    void ShowRequests();

    /**
     * Display an error message with the specified title and message
     *
     * @param title   The title of the error message
     * @param message The content of the error message
     */
    void showErrorMessage(String title, String message);

    /**
     * Open the driver's front page with the specified username
     *
     * @param username The username of the driver
     */
    void openDriverFrontPage(String username);

}
