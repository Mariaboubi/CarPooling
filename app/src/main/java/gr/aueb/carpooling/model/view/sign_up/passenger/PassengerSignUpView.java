package gr.aueb.carpooling.model.view.sign_up.passenger;

import gr.aueb.carpooling.model.view.View;

public interface PassengerSignUpView extends View {
    String getCardNumber();

    String getCardHolderName();

    String getCVV();

    void showErrorMessage(String s, String s1);


    /**
     * Success message for passenger registration
     * @param title , @param message
     *
     */
    void showRegistrationSuccessMessage(String title,String message);
}
