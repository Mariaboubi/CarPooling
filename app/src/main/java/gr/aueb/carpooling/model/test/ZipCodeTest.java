package gr.aueb.carpooling.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.test.util.BasicEqualTester;

public class ZipCodeTest {

    @Test
    public void equalsAndHashCode() {
        BasicEqualTester<ZipCode> equalsTester = new BasicEqualTester<>();

        // Test null zip code
        equalsTester.setObjectUnderTest(new ZipCode(null,0.0,0.0));
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.bothObjectsHaveNoState(new ZipCode(null,0.0,0.0));

        // Test valid zip code
        ZipCode zip = new ZipCode("11111",0.0,0.0);
        equalsTester.setObjectUnderTest(zip);
        equalsTester.otherObjectIsNull();
        equalsTester.otherObjectsHasNoState(new ZipCode(null,0.0,0.0));
        equalsTester.objectsHaveDifferentState(new ZipCode("222",0.0,0.0));
        equalsTester.sameReferences(equalsTester.getObjectUnderTest());
        equalsTester.bothObjectsHaveSameState(zip);

        zip.setCode("33333");
        assertEquals(zip.getCode(),"33333");

    }
    @Test
    public void isValidZipCode() {
        // Test valid zip code
        Assertions.assertTrue(ZipCode.isValid("12345"));

        // Test invalid zip code with non-digit characters
        Assertions.assertFalse(ZipCode.isValid("A1234"));

        // Test invalid zip code with fewer than 5 digits
        Assertions.assertFalse(ZipCode.isValid("1234"));

        // Test invalid zip code with more than 5 digits
        Assertions.assertFalse(ZipCode.isValid("123456"));
    }
    @Test
    public void testIsInvalidZipCode() {
        ZipCode zip = new ZipCode("88888", 0.0, 0.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> zip.setCode("11"));
        assertEquals("Invalid zipcode", exception.getMessage());
    }
    @Test
    public void testGetLatitude() {
        ZipCode zip = new ZipCode("11111",0.0,0.0);
        assertEquals(0.0, zip.getLatitude());
    }
    @Test
    public void testGetLongitude() {
        ZipCode zip = new ZipCode("11111",0.0,0.0);
        assertEquals(0.0, zip.getLongitude());
    }

}