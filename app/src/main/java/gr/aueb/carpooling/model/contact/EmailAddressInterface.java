package gr.aueb.carpooling.model.contact;

/**
 * Interface for EmailAddress functionalities.
 */
public interface EmailAddressInterface {

    /**
     * Gets the email address as a string.
     *
     * @return The email address.
     */
    String getAddress();

    /**
     * Checks if the email address is valid.
     *
     * @return true if the email address is valid, false otherwise.
     */
    boolean isValid();
}
