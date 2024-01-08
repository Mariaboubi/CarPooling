package gr.aueb.carpooling.model.view.driver.createRoute;

import org.threeten.bp.LocalDateTime;

import java.util.Currency;

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
        this.driverDAO = driverDAO;
        this.routeDAO = routeDAO;
    }


    public void setView(CreateRouteView view) {
        this.view = view;
    }

    public void onCreateRoute(String username) {

        driver = driverDAO.findByUsername(username);

        String street = view.Streeet();
        String number = view.Number();
        String City = view.City();
        String ZipCode = view.ZipCode();
        String cost = view.EstimatedCost();
        String numberpas = view.MaxPassengers();
        String date = view.Date();
        if (street.isEmpty() || number.isEmpty() || City.isEmpty() || ZipCode.isEmpty() ||
                cost.isEmpty() || numberpas.isEmpty() || date.isEmpty()) {
            view.showErrorMessage("Error!", "Συμπληρώστε όλα τα πεδία!.");

        } else if (street.length() < 2) {
            view.showErrorMessage("Error!", "Street can't be less than 3 characters.");
        } else if (Integer.parseInt(number) <= 0) {
            view.showErrorMessage("Error!", "Street number must be a positive number.");
        } else if (City.length() < 2) {
            view.showErrorMessage("Error!", "City can't be less than 3 characters.");
        } else if (ZipCode.length() != 5) {
            view.showErrorMessage("Error!", "Zip code must be a five digit number.");
        } else if (Integer.parseInt(cost) <= 0) {
            view.showErrorMessage("Error!", "Please provide a valid cost for your route.");
        } else if (Integer.parseInt(numberpas) <= 0) {
            view.showErrorMessage("Error!", "Please provide a valid maximum number of passengers.");
        } else if (!date.contains("T")) {
            view.showErrorMessage("Error!", "Incorrect date format. Please, check the example.");
        } else {
            final Currency euroCurrency = Currency.getInstance("EUR");
            ZipCode zipCode = new ZipCode(ZipCode);
            Address address = new Address(street, number, City, zipCode, "Greece");
            Money money = new Money(Double.parseDouble(cost), euroCurrency);
            Route route = new Route(driver, money, LocalDateTime.parse(date), address, Integer.parseInt(numberpas), false);

            routeDAO.save(route);
            driver.addRoute(route);
            view.showRouteAddedMessage();
        }
    }

    public void onBack() {
        view.goBack();
    }

    public CreateRouteView getView() {
        return this.view;
    }

    public Driver getDriver() {
        return this.driver;
    }
}
