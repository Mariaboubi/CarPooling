package gr.aueb.carpooling.model.view.driver.show_request;

import gr.aueb.carpooling.model.view.View;

public interface ShowRequestView  extends View {

    /**
     * Κρυβουμε το recyclerView και κάνουμε ορατό μήνυμα ενημέρωσης για την
     * απουσία εστιατορίων
     */
    void ShowNoRequests();

    /**
     * Εμφανίζουμαι και σετάρουμε το recyclerView και κάνουμε κρύβουμε το μηνυμα
     * απουσίας εστιατορίων
     */
    void ShowRequests();

    void showErrorMessage(String title, String message);

    void openDriverFrontPage(String username);

}
