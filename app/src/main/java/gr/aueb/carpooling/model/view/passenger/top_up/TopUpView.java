package gr.aueb.carpooling.model.view.passenger.top_up;

import gr.aueb.carpooling.model.view.View;

public interface TopUpView extends View {


    /**
     * Set the textView to display the necessary financial balance.
     */
    void setBalance(String balance);

    String getPassengerUername();

    void showErrorMessage(String title, String message);
}
