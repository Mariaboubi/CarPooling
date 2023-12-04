package gr.aueb.carpooling.model;

import java.util.HashSet;

import gr.aueb.carpooling.model.contact.Money;

/**
 * The PassengerInterface represents the interface for a user who is a passenger.
 */
public interface PassengerInterface {
    /**
     * Change the bank details of the passenger.
     *
     * @param cardNumber      The new card number
     * @param cardHolderName  The new card holder name
     * @param CVV             The new CVV (Card Verification Value)
     */
    void changeBankDetails(String cardNumber, String cardHolderName, String CVV);

    /**
     * Get the card number of the passenger.
     *
     * @return The card number
     */
    String getCardNumber();

    /**
     * Get the CVV (Card Verification Value) of the passenger's card.
     *
     * @return The CVV
     */
    String getCVV();

    /**
     * Get the card holder name of the passenger.
     *
     * @return The card holder name
     */
    String getCardHolderName();

    /**
     * Get the current balance of the passenger.
     *
     * @return The balance
     */
    Money getBalance();

    /**
     * Reset the balance of the passenger to zero.
     */
    void resetBalance();

    /**
     * Perform a financial transaction for the passenger.
     *
     * @param money The amount of money involved in the transaction
     * @return True if the transaction was successful, false otherwise
     */
    boolean transaction(Money money);

    /**
     * Top up the passenger's account balance.
     *
     * @param money The amount to top up
     * @throws IllegalStateException If the top-up amount is invalid (less than or equal to zero)
     */
    void topUp(Money money) throws IllegalStateException;

    /**
     * Make a payment using the passenger's account balance.
     *
     * @param cost The cost of the payment
     */
    void payment(Money cost) throws UnsupportedOperationException;

    /**
     * Add a route to the passenger's set of routes.
     *
     * @param route The route to be added
     * @return `true` if the route is added successfully, `false` if it already exists
     */
    boolean addRoute(Route route);

    /**
     * Remove a route from the passenger's set of routes.
     *
     * @param route The route to be removed.
     */
    boolean removeRoute(Route route) throws  UnsupportedOperationException;

    /**
     * Check if the passenger has a specific route.
     *
     * @param route The route to check for.
     * @return True if the passenger has the route, false otherwise.
     */
    boolean hasRoute(Route route);

    /**
     * Get the set of routes associated with the passenger.
     *
     * @return The set of routes associated with the passenger.
     */
    HashSet<Route> getRoutes();
}

