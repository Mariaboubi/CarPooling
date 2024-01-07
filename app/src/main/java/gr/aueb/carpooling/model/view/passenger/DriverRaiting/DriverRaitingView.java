package gr.aueb.carpooling.model.view.passenger.DriverRaiting;

import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.view.View;

public interface DriverRaitingView extends View {
    void showErrorMessage(String title,String message);

    String Politiness();

    String Security();

    String Cleanliness();

    void showRateAddedMessage(DriverRating rating);
}
