package gr.aueb.carpooling.model.view.passenger.top_up;

import gr.aueb.carpooling.model.view.View;

public interface TopUpView extends View {


    /**
     * Σετάρουμε το textView να δείχνει το απαραίτητο χρηματικό υπόλοιπο
     */
    void setBalance(String balance);

    String getPassengerUername();

    void showErrorMessage(String title, String message);
}
