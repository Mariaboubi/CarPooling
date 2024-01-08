package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import java.util.HashMap;

import gr.aueb.carpooling.model.view.View;

public interface RatingPassengerView extends View {

    //HashMap<String,String> getRateDetails();


    /**
     * Κρυβουμε το recyclerView και κάνουμε ορατό μήνυμα ενημέρωσης για την
     * απουσία εστιατορίων
     */
    void ShowNoPassengers();

    /**
     * Εμφανίζουμαι και σετάρουμε το recyclerView και κάνουμε κρύβουμε το μηνυμα
     * απουσίας εστιατορίων
     */
    void ShowPassengers();


    void showErrorMessage(String title,String message);

    void goToDriverFrontPage();

}
