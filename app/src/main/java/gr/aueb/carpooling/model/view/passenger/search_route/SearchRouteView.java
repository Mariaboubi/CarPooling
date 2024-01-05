package gr.aueb.carpooling.model.view.passenger.search_route;

import gr.aueb.carpooling.model.view.View;

public interface SearchRouteView extends View {




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


}


