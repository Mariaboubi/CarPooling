package gr.aueb.carpooling.model;

import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;

/**
 * The UserInterface represents the interface for a user in a carpooling system.
 */
public interface UserInterface {
    /**
     * Get the unique identifier for the user.
     *
     * @return The user's identifier
     */
    int getUserId();

    /**
     * Get the rating of the user.
     *
     * @return The user's rating
     */
    Rating getRate();

    /**
     * Set the rating for the user.
     *
     * @param rate The rating to set for the user
     */
    void setRating(Rating rate);

    /**
     * Get the username of the user.
     *
     * @return The username of the user
     */
    String getUsername();

    /**
     * Get the name of the user.
     *
     * @return The name of the user
     */
    String getName();

    /**
     * Get the surname of the user.
     *
     * @return The surname of the user
     */
    String getSurname();

    /**
     * Get the phone number of the user.
     *
     * @return The phone number of the user
     */
    String getPhone();

    /**
     * Get the email address of the user.
     *
     * @return The email address of the user
     */
    EmailAddress getEmail();

    /**
     * Get the password of the user.
     *
     * @return The password of the user
     */
    String getPassword();

    /**
     * Get the age of the user.
     *
     * @return The age of the user
     */
    String getAge();

    /**
     * Change the personal details of the user.
     *
     * @param username The new username
     * @param name     The new name
     * @param surname  The new surname
     * @param phone    The new phone number
     * @param email    The new email address
     * @param age      The new age
     */
    void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email,
                               String age);

    /**
     * Change the password of the user.
     *
     * @param pass The new password
     */
    void changePassword(String pass);

    void setBalance(Money balance);
}
