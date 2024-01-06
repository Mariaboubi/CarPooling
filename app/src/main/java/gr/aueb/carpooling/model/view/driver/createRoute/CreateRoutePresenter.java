package gr.aueb.carpooling.model.view.driver.createRoute;

import org.threeten.bp.LocalDateTime;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;

public class CreateRoutePresenter {

    private RouteDAO routeDAO;
    private DriverDAO driverDAO = new DriverDAOmemory();

    CreateRouteView view;
    private int driverId;

    private Driver driver;

    public CreateRoutePresenter(DriverDAO driverDAO, RouteDAO routeDAO) {
        this.driverDAO= driverDAO;
        this.routeDAO = routeDAO;
    }

    public void setDriver(int id){
        driver= driverDAO.find(id);
    }

    public void setView(CreateRouteView view) {
        this.view = view;
    }
    public void onCreateRoute(String username) {
//

        driver= driverDAO.findByUsername(username);

        String street = view.Streeet();
        String number = view.Number();
        String City = view.City();
        String ZipCode = view.ZipCode();
        String cost = view.EstimatedCost();
        String numberpas = view.MaxPassengers();
        String date= view.Date();
        if (street.isEmpty() || number.isEmpty() || City.isEmpty() || ZipCode.isEmpty() ||
                cost.isEmpty() || numberpas.isEmpty() || date.isEmpty()) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία!.");

        } else if (street.length() < 2 ) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο Street.");
        } else if (Integer.parseInt(number) < 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό στο νουμερο του δρομου.");
        }else if (City.length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο City");
        } else if (ZipCode.length() !=5) {
           view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 5 ψηφία στον Ταχυδρομικό κώδικα(ZipCode).");
        }else if (Integer.parseInt(cost)<0){
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό");
        } else if (Integer.parseInt(numberpas)<0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε εγκυρο αριθμο συνεπιβατων");
        }  else if (!date.contains("T")) {
                view.showErrorMessage("Σφάλμα!", "Συμπληρώστε την ημερομηνια σθμφωνα με το παραδειγμα");
        } else {
            final Currency euroCurrency = Currency.getInstance("EUR");
            ZipCode zipCode= new ZipCode(ZipCode,0.0,0.0);
            Address address= new Address(street,number,City,zipCode,"Greece");
            Money money= new Money(Double.parseDouble(cost),euroCurrency);

            Route route = new Route(driver,money, LocalDateTime.parse(date),address,Integer. parseInt(numberpas),false);

            routeDAO.save(route);
            driver.addRoute(route);
            view.showRouteAddedMessage();
      }
    }

    public void onBack(){
        view.goBack();
    }

    public CreateRouteView getView(){
        return this.view;
    }

    public Driver getDriver(){
        return this.driver;
    }
}
