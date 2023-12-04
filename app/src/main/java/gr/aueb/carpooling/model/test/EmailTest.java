package gr.aueb.carpooling.model.test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.test.util.BasicEqualTester;

public class EmailTest {

    @Test
    public void equalsAndHashCode() {

        BasicEqualTester<EmailAddress> equalsTester = new BasicEqualTester<EmailAddress>();
        EmailAddress email = new EmailAddress("a@b.gr");
        equalsTester.setObjectUnderTest(new EmailAddress("test@mail.com"));
        equalsTester.otherObjectIsOfDifferentType(new Object());
        equalsTester.setObjectUnderTest(email);
        Assertions.assertTrue(email.isValid());

        equalsTester.otherObjectsHasNoState(new EmailAddress("test@mail.com"));
        equalsTester.objectsHaveDifferentState(new EmailAddress("123@123.com"));
        equalsTester.bothObjectsHaveSameState(new EmailAddress("a@b.gr"));

        EmailAddress email2 = equalsTester.getObjectUnderTest();
        equalsTester.sameReferences(email2);
    }

}