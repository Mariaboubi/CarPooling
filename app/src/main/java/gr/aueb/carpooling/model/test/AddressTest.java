package gr.aueb.carpooling.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.test.util.BasicEqualTester;

public class AddressTest {
    @Test
    public void testEqualsObject() {
        Address address1 = new Address();
        Address address2 = new Address();

        Assertions.assertNotEquals(null, address1);
        Assertions.assertEquals(address1, address2);
        Assertions.assertEquals(address1.hashCode(), address2.hashCode());

        address1.setCity("Ellada");
        Assertions.assertNotEquals(address1, address2);
        Assertions.assertNotEquals(address1.hashCode(), address2.hashCode());
        address2.setCity("Ellada");
        Assertions.assertEquals(address1, address2);
        Assertions.assertEquals(address1.hashCode(), address2.hashCode());
    }
    @Test
    public void testEqualsAndHashCode() {
        BasicEqualTester<Address> equalsTester = new BasicEqualTester<Address>();
        Address address = new Address();
        equalsTester.setObjectUnderTest(address);

        equalsTester.otherObjectIsNull();

        equalsTester.otherObjectIsOfDifferentType(new Object());

        Address address2 = new Address();
        equalsTester.bothObjectsHaveNoState(address2);

        address.setStreet("Patision");
        Assertions.assertEquals("Patision", address.getStreet());
        equalsTester.otherObjectsHasNoState(address2);

        equalsTester.sameReferences(address);

        address2.setStreet("Patision");
        equalsTester.bothObjectsHaveSameState(address2);

        address.setNumber("76");
        Assertions.assertEquals("76", address.getNumber());
        equalsTester.objectsHaveDifferentState(address2);

        address2.setNumber("87");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setNumber("76");
        equalsTester.bothObjectsHaveSameState(address2);

        address.setCity("Athens");
        Assertions.assertEquals("Athens", address.getCity());
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCity("Lamia");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCity("Athens");
        equalsTester.bothObjectsHaveSameState(address2);

        address2.setCountry("Italy");
        equalsTester.objectsHaveDifferentState(address2);

        address2.setCountry("Greece");
        Assertions.assertEquals("Greece", address.getCountry());
        equalsTester.bothObjectsHaveSameState(address2);

        address.setZipCode(new ZipCode("11111",0.0,0.0));
        equalsTester.objectsHaveDifferentState(address2);

        address2.setZipCode(new ZipCode("22222",0.0,0.0));
        equalsTester.objectsHaveDifferentState(address2);

        address2.setZipCode(new ZipCode("11111",0.0,0.0));
        equalsTester.bothObjectsHaveSameState(address2);

    }
}
