package gr.aueb.carpooling.model.view.driver.rating_passengers;

import gr.aueb.carpooling.model.view.View;

public interface RatingPassengerView extends View {


    /**
     * Hide the recyclerView and display a visible message indicating the absence of passengers
     */
    void ShowNoPassengers();

    /**
     * Display and set up the recyclerView, and hide the message indicating the absence of passengers
     */
    void ShowPassengers();

    /**
     * Display an error message with the specified title and message
     *
     * @param title   The title of the error message
     * @param message The content of the error message
     */
    void showErrorMessage(String title, String message);

    /**
     * Navigate to the driver's front page
     */
    void goToDriverFrontPage();

}
