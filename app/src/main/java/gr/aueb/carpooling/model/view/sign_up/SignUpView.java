package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.view.View;

public interface SignUpView extends View {

    String getName();

    String getSurname();

    String getAge();

    String getEmail();

    String getPhoneNumber();

    String getCardNumber();

    String getCardHolderName();

    String getCVV();

    String getUsername();

    String getPassword();

    String getPasswordVerification();

    String getDriverLicense();

    String getCarType();

    String getIban();

    void showErrorMessage(String s, String s1);


    /**
     * Successful registration message
     */
    void showRegistrationSuccessMessage();




}
