package gr.aueb.carpooling.model.view.driver.ExistedRoutes;

import gr.aueb.carpooling.model.view.View;

public interface ExitedRouteView extends View {

    void goBack();


    /**
     * Κρυβουμε το recyclerView και κάνουμε ορατό μήνυμα ενημέρωσης για την
     * απουσία εστιατορίων
     */
    void ShowNoRoutes();

    /**
     * Εμφανίζουμαι και σετάρουμε το recyclerView και κάνουμε κρύβουμε το μηνυμα
     * απουσίας εστιατορίων
     */
    void ShowRoutes();

    void showErrorMessage(String title, String message);
}
