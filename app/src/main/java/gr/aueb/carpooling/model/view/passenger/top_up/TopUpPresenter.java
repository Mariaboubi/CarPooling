package gr.aueb.carpooling.model.view.passenger.top_up;

import java.util.Currency;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;

public class TopUpPresenter {

    private TopUpView view;
    private PassengerDAO passengerDAO;
    private Passenger passenger;


    private Money money;

    public TopUpPresenter(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public void setView(TopUpView view) {
        this.view = view;
    }

    public TopUpView getView() {
        return view;
    }

    public void setPassenger() {
        passenger = passengerDAO.findByUsername(view.getPassengerUername());
//        view.showErrorMessage("username", passenger.getUsername());
    }

    public Passenger getPassenger() {
        return passenger;
    }


    /**
     * Εαν το instance του επιβατη δεν είναι null εμφανίζουμε το χρηματικό του υπόλοιπο
     * αλλιως εμφανίζουμε Error
     */

    public void setLayout() {
        if (passenger!=null)
        {
           String balance =String.valueOf(passenger.getBalance().getAmount());
           view.setBalance("Balance "+ balance + " €");
        }
        else
        {
            view.setBalance("ERROR");
        }

    }

    /**
     * Εαν το instance του επιβατη δεν είναι null
     * του προσθέτουμε ένα χρηματικό ποσό και
     * καλούμε SetLayout() για να ανανεώσουμε
     * το υπολοιπο που φαίνεται στην οθόνη
     */
    public void onTopUp(double amount) {
        if(passenger!=null) {
            money = new Money(amount,Currency.getInstance("EUR"));
            passenger.topUp(money);
            setLayout();
        }
    }

}
