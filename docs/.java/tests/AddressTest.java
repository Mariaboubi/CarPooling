package carPooling;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {
    private Address address;

    @Before
    public void setup() {
        address = new Address();
    }

    @Test
    public void testGetSetStreetName() {
        String streetName = "Example Street";
        address.setStreetName(streetName);
        assertEquals(streetName, address.getStreetName());
    }

    @Test
    public void testGetSetStreetNumber() {
        int streetNumber = 123;
        address.setStreetNumber(streetNumber);
        assertEquals(streetNumber, address.getStreetNumber());
    }

    @Test
    public void testGetSetZC() {
        String ZC = "12345";
        address.setZC(ZC);
        assertEquals(ZC, address.getZC());
    }
}
