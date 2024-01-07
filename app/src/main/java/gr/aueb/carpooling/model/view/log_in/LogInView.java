package gr.aueb.carpooling.model.view.log_in;

import gr.aueb.carpooling.model.view.View;

public interface LogInView extends View {
    /**
     * This method retrieves the username entered by the user in the Username field.
     */
    String extractUsername();

    /**
     * This method retrieves the password entered by the user in the Password field.
     */
    String extractPassword();


    /**
     * This method is called when the registration button for a customer is pressed.
     */
    void openSignupActivity();

    /**
     * Displays an alert-type message with the specified title and message.
     *
     * @param title   The title of the message.
     * @param message The content of the message.
     */
    void showErrorMessage(String title, String message);

    /**
     * This method is called when the log in is successful and the user goes to the attribute selection page.
     */
    void openAttributeSelectionActivity(String username);
}

