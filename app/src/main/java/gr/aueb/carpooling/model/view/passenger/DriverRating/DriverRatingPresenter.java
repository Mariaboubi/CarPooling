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

         double pol = 0;
         double sec = 0;
         double clean = 0;
         if(!politeness.isEmpty()){
             pol = Double.valueOf(politeness);
         }
        if(!security.isEmpty()){
            sec = Double.valueOf(security);
        }
        if(!cleanliness.isEmpty()){
            clean = Double.valueOf(cleanliness);
        }

        if (politeness.isEmpty() || security.isEmpty() || cleanliness.isEmpty()) {
            view.showErrorMessage("Error!", "Complete all the fields or press check button.");
        }
         else if(pol < 0 || pol > 5) {
            view.showErrorMessage("Error!", "Politeness must be between 0 and 5.");
        } else if(sec < 0 || sec > 5) {
            view.showErrorMessage("Error!", "Security must be between 0 and 5.");
        } else if(clean < 0 || clean > 5) {
            view.showErrorMessage("Error!", "Cleanliness must be between 0 and 5.");
        }
        else {
            Driver driver = route.getDriver();
            DriverRating driverRating = new DriverRating(driver,route,politeness,security,cleanliness);

            driverRating.addRate(passenger ,driverRating);

            driver.addRates(driverRating);

            driverRatingDAO.save(driverRating);

            view.RateAdded();

        }

    }

    public boolean checkButtonCanBePressed() {
        String politeness = view.politeness();
        String security = view.security();
        String cleanliness = view.cleanliness();

        if (politeness.isEmpty() && security.isEmpty() && cleanliness.isEmpty()) {
            return true;
        }
        return false;
    }
}
