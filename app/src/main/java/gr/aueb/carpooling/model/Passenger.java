package gr.aueb.carpooling.model;

import java.util.*;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;

public class Passenger extends User implements PassengerInterface {

    private String cardNumber, cardHolderName, CVV; // Personal details
    private Money balance; // The money that Passenger puts in the app in order to pay
    private final Set<Route> routes; // A HashSet of routes that the Passenger takes part

    private final int passenger_id;

    private Set<PassengerRating> rates;
    // Constructor
    public Passenger(String username, String name, String surname, String phone, EmailAddress email,
                     String password, String age, String cardNumber, String cardHolderName, String CVV) {
        super(username, name, surname, phone, email, password, age);// calling the father constructor(User)
        passenger_id = super.getUserId();
        changeBankDetails(cardNumber, cardHolderName, CVV);
        Currency euroCurrency = Currency.getInstance("EUR");
        this.balance = super.getBalance();
        routes = new HashSet<>();
        this.rates = new HashSet<>();
    }



    public void changeBankDetails(String cardNumber, String cardHolderName, String CVV) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.CVV = CVV;
    }

    public void addRating(PassengerRating rating) {
        this.rates.add(rating);
    }

    public float averageRating(){
        int size= rates.size();
        float sum=0;
        for(PassengerRating rates: rates){
            sum+= rates.averageRating();
        }
        return rates.size() != 0 ? (float) (sum / size) : 0;
    }


    public int getPassengerId() {
        return passenger_id;
    }
    // Getters for personal details
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCVV() {
        return this.CVV;
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public Money getBalance() {
        return super.getBalance();
    }


    public void resetBalance() {
        super.resetBalance();
    }

    public boolean transaction(Money money) {
        boolean success = false;
        if (this.getBalance().getAmount().compareTo(money.getAmount()) >= 0) {
            this.balance = this.balance.minus(money);
            success = true;
        }
        return success;
    }

    // Method to top up a balance
    public void topUp(Money money) {
        super.topUp(money);
    }



    // Methods for managing routes
    public boolean addRoute(Route route) {
        return this.routes.add(route);
    }

    public boolean removeRoute(Route route) throws  UnsupportedOperationException {
        if(this.routes.isEmpty()){
            throw new UnsupportedOperationException("Cannot remove from an empty route_data set.");
        }
        return this.routes.remove(route);
    }

    public boolean hasRoute(Route route) {
        return routes.contains(route);
    }

//    public HashSet<Route> getRoutes() {
//        return new HashSet<>(routes);
//    }
}
