package gr.aueb.carpooling.model.view.driver.top_up;

import java.text.DecimalFormat;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.dao.DriverDAO;

public class DriverTopUpPresenter {

    private DriverTopUpView view;
    private final DriverDAO driverDAO;
    private Driver driver;

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
        driver = driverDAO.findByUsername(view.getDriverUsername());
    }

    public Driver getDriver() {
        return driver;
    }


    /**
     * If the instance of the driver is not null, display the monetary balance;
     * otherwise, display an error.
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
}
