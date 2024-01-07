package gr.aueb.carpooling.model.view.passenger.DriverRating;

import gr.aueb.carpooling.model.DriverRating;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class DriverRatingPresenter {
    private DriverRatingDAO driverRatingDAO;

    private RouteDAO routeDAO =new RouteDAOmemory();

    private PassengerDAO passengerDAO =new PassengerDAOmemory();

    DriverRatingView view;
     public DriverRatingPresenter(DriverRatingDAO driverRatingDAO){
         this.driverRatingDAO=driverRatingDAO;
     }

    public void setView(DriverRatingView view) {
        this.view = view;
    }

    public DriverRatingView getView(){
        return this.view;
    }

    public void onCreateRate(String username,Route route) {
         String politiness= view.Politiness();
         String security= view.Security();
         String cleanliness= view.Cleanliness();
        Passenger pas= passengerDAO.findByUsername(username);

        if (politiness.isEmpty() || security.isEmpty() || cleanliness.isEmpty()) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία!.");
        }else {
            DriverRating driverRating=new DriverRating(route.getDriver(),route,politiness,security,cleanliness);
            driverRatingDAO.save(driverRating);
            driverRating.addRate(pas,driverRating);
            route.getDriver().addRates(driverRating);
            view.showRateAddedMessage(driverRating);

        }

    }
}
