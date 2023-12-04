package gr.aueb.carpooling.model.contact;

import androidx.annotation.NonNull;

import java.util.Currency;

public class Money {

    private Double amount;
    private Currency currency;

    public Money(Double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money plus(Money other) {
        checkForSameCurrencies(other);
        return new Money(amount + other.amount, currency);
    }

    public Money minus(Money other) {
        checkForSameCurrencies(other);
        return new Money(amount-other.amount, currency);
    }

    public Money times(Double factor) {
        return new Money(amount * factor, currency);
    }

    public Money divide(Double divisor) {
        return new Money(amount / divisor, currency);
    }


    private void checkForSameCurrencies(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Different coins");
        }
    }

    public static Money euros(Double amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    public static Money dollars(Double amount) {
        return new Money(amount, Currency.getInstance("USD"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Money)) {
            return false;
        }

        Money theMoney = (Money) other;
        if (currency == null) {
            return theMoney.currency == null && amount == 0.0 && theMoney.amount == 0.0;
        }

        if (!currency.equals(theMoney.currency)) {
            return false;
        }

        return amount == 0.0 ? theMoney.amount == 0.0
                : (amount.compareTo(theMoney.amount) == 0);
    }


    @Override
    public int hashCode() {
        return amount == null ? 0 : amount.hashCode();
    }

}