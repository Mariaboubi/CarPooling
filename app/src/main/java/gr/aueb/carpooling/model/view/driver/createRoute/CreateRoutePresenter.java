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

public class CreateRoutePresenter {

    private RouteDAO routeDAO;
    private DriverDAO driverDAO;

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
    public void onCreateRoute() {
        boolean isEmpty = false;
        HashMap<String, String> details = view.getRouteDetails();

        for (Map.Entry<String, String> set : details.entrySet()) {
            if (set.getValue().isEmpty() || set.getValue() == null) {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία!.");
        } else if (details.get("Street").length() < 2 ) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο Street.");
        } else if (details.get("Street Number").length() < 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό.");
        }else if (details.get("City").length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο City");
        } else if (details.get("ZipCode").length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 2 και πάνω ψηφία στον Ταχυδρομικό κώδικα(ZipCode).");
        }else if (details.get("Estimated Cost").equals("0")){
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό");
        } else if (details.get("Max number of passengers").length()>2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε εγκυρο αριθμο συνεπιβατων");
        } else {
            final Currency euroCurrency = Currency.getInstance("EUR");
            ZipCode zipCode= new ZipCode(details.get("ZipCode"),0.0,0.0);
            Address address= new Address(details.get("Street"),details.get("Street Number"),details.get("City"),zipCode,"Greece");
            Money money= new Money(Double.parseDouble(details.get("Estimated Cost")),euroCurrency);
            Route route = new Route(driver,money, LocalDateTime.parse(details.get("Date")),address,Integer. parseInt(details.get("Max number of passengers")),false);

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
