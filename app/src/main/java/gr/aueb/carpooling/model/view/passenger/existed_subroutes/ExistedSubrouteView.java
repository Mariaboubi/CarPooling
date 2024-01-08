package gr.aueb.carpooling.model.view.passenger.existed_subroutes;

import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.view.View;

public interface ExistedSubrouteView extends View {



    /**
     * Hide the recyclerView and display a visible message for the absence of subroutes.
     */
    void ShowNoSubroutes();

    /**
     * Display and set up the recyclerView and hide the message for the absence of subroutes.
     */
    void ShowSubroutes();

    /**
     * Display an error message.
     * @param title The title of the error message.
     * @param message The content of the error message.
     */
    void showErrorMessage(String title, String message);

    /**
     * Perform a payment operation for the specified subroute.
     * @param sub The subroute for which the payment is to be performed.
     * @return True if the payment is successful, false otherwise.
     */
    boolean payment(Subroute sub);

}
