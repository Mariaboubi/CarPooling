package gr.aueb.carpooling.model;
import java.util.Currency;

import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;

public class User implements UserInterface {
    private static int userIdCounter = 0; // Counter for User Objects
    private final int userId; // The id of the user
    private EmailAddress email; // User's Email Address
    private String username, password, surname, name, phone; // Personal details
    private String age; // User's age
    public Rating rate; // User's rating

    private Money balance;

    // Constructor
    public User(String username, String name, String surname, String phone, EmailAddress email, String password,
                String age) {
        this.userId = ++userIdCounter;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
        this.rate = null;
        Currency euroCurrency = Currency.getInstance("EUR");
        this.balance = new Money(0.0, euroCurrency);
    }

    // Getters ans Setters
    public int getUserId() { return this.userId; }
    public Rating getRate() {
        return this.rate;
    }

    public Money getBalance() {
        return this.balance;
    }


    public void resetBalance() {
        this.balance = Money.euros(0.0);
    }

    public void topUp(Money money) {
        if (money.getAmount().compareTo(0.0) > 0) {
            this.balance = this.balance.plus(money);

        }
    }

    public void setRating(Rating rate) {
        this.rate = rate;
    }
    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }
    public String getSurname() {
        return this.surname;
    }
    public String getPhone() {
        return this.phone;
    }

    public EmailAddress getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getAge() {
        return this.age;
    }
    public void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email,
                                      String age) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }
    public void changePassword(String pass) {
        this.password = pass;
    }
}
