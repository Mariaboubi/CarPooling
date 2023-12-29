package gr.aueb.carpooling.model.view.sign_up;

import gr.aueb.carpooling.model.view.View;
import gr.aueb.carpooling.model.contact.EmailAddress;

public interface SignUpView extends View {


    /**
     *Μεθόδοι για να λάβουμε τα δεδομένα που εισήχθησαν στα πεδία
     */
    String getName();

    String getSurname();

    Integer getAge();

    EmailAddress getEmail();

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


    /** Μηνυμα επητυχης εγγραφης χριστη
     */
    void showRegistrationSuccessMessage();


    /** Καλείται για να επιστρέψουμε στο προηγούμενο Activity
     */
    void goBack();


}
