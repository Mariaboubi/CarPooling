package gr.aueb.carpooling.model.view.passenger.driver_rating;

import gr.aueb.carpooling.model.view.View;

public interface DriverRatingView extends View {
    void showErrorMessage(String title,String message);

    String politeness();

    String security();

    String cleanliness();

    void RateAdded();
}
