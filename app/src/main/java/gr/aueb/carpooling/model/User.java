package gr.aueb.carpooling.model;
import gr.aueb.carpooling.model.contact.EmailAddress;

public class User implements UserInterface {
    private static int userIdCounter = 0; // Counter for User Objects
    private final int userId; // The id of the user
    private EmailAddress email; // User's Email Address
    private String username, password, surname, name, phone; // Personal details
    private int age; // User's age
    public Rating rate; // User's rating

    // Constructor
    public User(String username, String name, String surname, String phone, EmailAddress email, String password,
                int age) {
        this.userId = ++userIdCounter;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
        this.rate = null;
    }

    // Getters ans Setters
    public int getUserId() { return this.userId; }
    public Rating getRate() {
        return this.rate;
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
    public int getAge() {
        return this.age;
    }
    public void changePersonalDetails(String username, String name, String surname, String phone, EmailAddress email,
                                      int age) {
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
