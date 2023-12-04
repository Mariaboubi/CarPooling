package gr.aueb.carpooling.model.contact;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Interface for Money functionalities.
 */
public interface MoneyInterface {

    /**
     * Gets the monetary amount.
     *
     * @return The monetary amount as BigDecimal.
     */
    double getAmount();

    /**
     * Gets the currency of the monetary amount.
     *
     * @return The currency.
     */
    Currency getCurrency();

    /**
     * Adds the specified Money to this Money, ensuring the currencies are the same.
     *
     * @param other The Money to be added.
     * @return A new Money instance representing the sum.
     * @throws IllegalArgumentException if the currencies are different.
     */
    Money plus(Money other);

    /**
     * Subtracts the specified Money from this Money, ensuring the currencies are the same.
     *
     * @param other The Money to be subtracted.
     * @return A new Money instance representing the difference.
     * @throws IllegalArgumentException if the currencies are different.
     */
    Money minus(Money other);

    /**
     * Multiplies this Money by a double factor.
     *
     * @param factor The factor by which the Money is to be multiplied.
     * @return A new Money instance representing the product.
     */
    Money times(double factor);

    /**
     * Multiplies this Money by a long factor.
     *
     * @param factor The factor by which the Money is to be multiplied.
     * @return A new Money instance representing the product.
     */
    Money times(long factor);

    /**
     * Divides this Money by a double divisor.
     *
     * @param divisor The divisor by which the Money is to be divided.
     * @return A new Money instance representing the quotient.
     */
    Money divide(double divisor);

    /**
     * Divides this Money by a long divisor.
     *
     * @param divisor The divisor by which the Money is to be divided.
     * @return A new Money instance representing the quotient.
     */
    Money divide(long divisor);
}
