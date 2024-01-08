package gr.aueb.carpooling.model.view.driver.top_up;

import java.text.DecimalFormat;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.DriverDAO;

public class DriverTopUpPresenter {

    private DriverTopUpView view;
    private DriverDAO driverDAO;
    private Driver driver;


    private Money money;

    public DriverTopUpPresenter(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public void setView(DriverTopUpView view) {
        this.view = view;
    }

    public DriverTopUpView getView() {
        return view;
    }

    public void setDriver() {
        driver = driverDAO.findByUsername(view.getDriverUername());
//        view.showErrorMessage("username", passenger.getUsername());
    }

    public Driver getDriver() {
        return driver;
    }


    /**
     * Εαν το instance του επιβατη δεν είναι null εμφανίζουμε το χρηματικό του υπόλοιπο
     * αλλιως εμφανίζουμε Error
     */

    public void setLayout() {
        if (driver!=null)
        {
            User user=driver;
            String balance =new DecimalFormat("0.00").format(user.getBalance().getAmount());
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
//    public void onTopUp(double amount) {
//        if(d!=null) {
//            money = new Money(amount, Currency.getInstance("EUR"));
//            passenger.topUp(money);
//            setLayout();
//        }
//    }

}
