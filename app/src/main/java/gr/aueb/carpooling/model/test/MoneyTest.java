package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Currency;

import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.test.util.BasicEqualTester;

public class MoneyTest {

    private Currency euroCurrency = Currency.getInstance("EUR");

    @Test
    public void equalsAndHashCode() {

        BasicEqualTester<Money> equalsTester = new BasicEqualTester<Money>();

        equalsTester.setObjectUnderTest(new Money(null, null));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());

        equalsTester.setObjectUnderTest(Money.euros(5.0));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new Money(null, null));
        equalsTester.objectsHaveDifferentState(Money.euros(10.0));

        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(Money.euros(5.0));
    }

    @Test
    public void plusSameCurrencies() {
        Money a = Money.euros(10.0);
        Money b = Money.euros(4.0);
        Money c = a.plus(b);
        assertEquals(14.0, c.getAmount(), 0.0);
        assertEquals(euroCurrency, c.getCurrency());
        assertEquals(10.0, a.getAmount(), 0.0);
        assertEquals(4.0, b.getAmount(), 0.0);
    }

    @Test
    public void plusDifferentCurrencies() {
        Money a = Money.euros(10.0);
        Money b = Money.dollars(5.0);
        assertThrows(IllegalArgumentException.class, () -> a.plus(b));
    }

    @Test
    public void minusSameCurrencies() {
        Money a = Money.euros(10.0);
        Money b = Money.euros(4.0);
        Money c = a.minus(b);
        assertEquals(6.0, c.getAmount());
        assertEquals(euroCurrency, c.getCurrency());
        assertEquals(10.0, a.getAmount(), 0.0);
        assertEquals(4.0, b.getAmount(), 0.0);
    }

    @Test
    public void minusDifferentCurrencies() {
        Money a = Money.euros(10.0);
        Money b = Money.dollars(5.0);
        assertThrows(IllegalArgumentException.class, () -> a.minus(b));

    }

    @Test
    public void multiply() {
        Money a = Money.euros(10.0);
        Money b = a.times(5.0);
        assertEquals(10.0, a.getAmount(), 0.0);
        assertEquals(euroCurrency, a.getCurrency());

        assertEquals(50.0, b.getAmount(), 0.0);
        assertEquals(euroCurrency, b.getCurrency());

        b = a.times(5.0);
        assertEquals(10.0, a.getAmount(), 0.0);
        assertEquals(euroCurrency, a.getCurrency());

        assertEquals(50.0, b.getAmount(), 0.0);
        assertEquals(euroCurrency, b.getCurrency());
    }

    @Test
    public void divide() {
        Money a = Money.euros(10.0);
        Money b = a.divide(5.0);
        assertEquals(10.0, a.getAmount(), 0.0);
        assertEquals(euroCurrency, a.getCurrency());

        assertEquals(Double.valueOf(2.0), b.getAmount());
        assertEquals(euroCurrency, b.getCurrency());

        b = a.divide(5.0);
        assertEquals(Double.valueOf(10.0), a.getAmount());
        assertEquals(euroCurrency, a.getCurrency());

        assertEquals(Double.valueOf(2.0), b.getAmount());
        assertEquals(euroCurrency, b.getCurrency());
    }
}