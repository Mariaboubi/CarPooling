package gr.aueb.carpooling.model.view.passenger.DriverRating;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class DriverRatingPresenter {

    private final DriverRatingDAO driverRatingDAO;

    DriverRatingView view;

     public DriverRatingPresenter(DriverRatingDAO driverRatingDAO){
         this.driverRatingDAO = driverRatingDAO;
     }

    public void setView(DriverRatingView view) {
        this.view = view;
    }

    public DriverRatingView getView(){
        return this.view;
    }

    public void onCreateRate(Passenger passenger,Route route) {
         String politeness = view.politeness();
         String security = view.security();
         String cleanliness = view.cleanliness();

        if (politeness.isEmpty() || security.isEmpty() || cleanliness.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields or press check button.");
        }else {


            Driver driver = route.getDriver();
            DriverRating driverRating = new DriverRating(driver,route,politeness,security,cleanliness);

            driverRating.addRate(passenger ,driverRating);

            driver.addRates(driverRating);

            driverRatingDAO.save(driverRating);

            view.showRateAddedMessage(driverRating);

        }

    }

    public boolean checkIfCanBePressed() {
        String politeness = view.politeness();
        String security = view.security();
        String cleanliness = view.cleanliness();

        if (politeness.isEmpty() || security.isEmpty() || cleanliness.isEmpty()) {
            return false;
        }
        return true;
    }
}
