package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

public interface ExistedSubrouteView {

    void goBack();


    /**
     * Κρυβουμε το recyclerView και κάνουμε ορατό μήνυμα ενημέρωσης για την
     * απουσία διαδρομων
     */
    void ShowNoSubroutes();

    /**
     * Εμφανίζουμαι και σετάρουμε το recyclerView και κάνουμε κρύβουμε το μηνυμα
     * απουσίας διαδρωμων
     */
    void ShowSubroutes();

    void showErrorMessage(String title, String message);
}
