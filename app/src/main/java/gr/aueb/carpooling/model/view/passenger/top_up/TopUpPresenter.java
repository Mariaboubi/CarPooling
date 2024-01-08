package gr.aueb.carpooling.model.view.passenger.top_up;

import java.text.DecimalFormat;
import java.util.Currency;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.PassengerDAO;

public class TopUpPresenter {

    private TopUpView view;
    private final PassengerDAO passengerDAO;
    private Passenger passenger;


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
    }

    public Passenger getPassenger() {
        return passenger;
    }


    /**
     * If the passenger instance is not null, display their financial balance;
     * otherwise, display an error.
     */
    public void setLayout() {
        if (passenger!=null) {
            User user = passenger;
           String balance = new DecimalFormat("0.00").format(user.getBalance().getAmount());
           view.setBalance("Balance "+ balance + " €");
        } else {
            view.setBalance("ERROR");
        }

    }

    /**
     * If the passenger instance is not null, add a monetary amount,
     * and call setLayout() to refresh the displayed balance on the screen.
     */
    public void onTopUp(double amount) {
        if(passenger!=null) {
            Money money = new Money(amount, Currency.getInstance("EUR"));
            passenger.topUp(money);
            setLayout();
        }
    }

}
