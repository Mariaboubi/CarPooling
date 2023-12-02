import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PassengerTest {
    Passenger passenger;
    EmailAddress email= new EmailAddress("pappas@gmail.com");

    @Before
    public void setUp() {
        
        passenger = new Passenger(1,"john123", "john", "pappas", "6969497299", email, "12345123", 19,4.2, "12222",
                "john", "322");
    }

    @After
    public void tearDown() {
        passenger.resetBalance();
        passenger = null;
    }

    @Test
    public void getUserId() {
        assertEquals(passenger.getUserId(), 1);
    }

    @Test
    public void getUsername() {
        assertEquals(passenger.getUsername(), "john123");
    }

    @Test
    public void getName() {
        assertEquals(passenger.getName(), "john");
    }

    @Test
    public void getSurname() {
        assertEquals(passenger.getSurname(), "pappas");
    }

    @Test
    public void getTelephone() {
        assertEquals(passenger.getPhone(), "6969497299");
    }

    @Test
    public void getEmail() {
        assertEquals(passenger.getEmail(), "pappas@gmail.com");
    }

    @Test
    public void getPassword() {
        assertEquals(passenger.getPassword(), "12345123");
    }

    @Test
    public void getAge() {
        assertEquals(passenger.getAge(), 19);
    }

    
    @Test
    public void getRate() {
        assertEquals(passenger.getRate(), 4.2);
    }



    @Test
    public void changePersonalDetails() {// we decide to change only the username
        passenger.changePersonalDetails("john322", "john", "pappas", "696949", email,19);
        assertEquals(passenger.getUsername(), "john322");
    }

    @Test
    public void changePassword() {
        passenger.changePassword("123454321");
        assertEquals(passenger.getPassword(), "123454321");
    }

    @Test
    public void changeBankDetails() { // we decide to change the cardNumber and keep everything else the same
        passenger.changeBankDetails("12345", "john", "322"); // might need to call GETTERS to show that these stay the
                                                            // same
        assertEquals(passenger.getCardNumber(), "12345");
    }

    @Test
    public void getCardNumber() {
        assertEquals(passenger.getCardNumber(), "12222");
    }

    @Test
    public void getCVV() {
        assertEquals(passenger.getCVV(), "322");
    }

    @Test
    public void getCardHolderName() {
        assertEquals(passenger.getCardHolderName(), "john");
    }

    @Test
    public void getBalance() {
        assertEquals(passenger.getBalance(), 0.00, 0.00);
    }

    @Test
    public void topUp() {
        passenger.topUp(10.00);
        assertEquals(passenger.getBalance(), 10.00, 0.00);
    }

    @Test
    public void transaction() {
        passenger.topUp(10);
        passenger.transaction(5.00);
        assertEquals(passenger.getBalance(), 5.00, 0.00);
    }

    @Test
    public void resetBalance() {
        passenger.topUp(10.0);
        assertEquals(passenger.getBalance(), 10.00, 0.00);
        passenger.resetBalance();
        assertEquals(passenger.getBalance(), 0.00, 0.00);
    }
}