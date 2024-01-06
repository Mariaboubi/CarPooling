package gr.aueb.carpooling.model.view.passenger.DriverRaiting;

import gr.aueb.carpooling.model.dao.DriverRatingDAO;
import gr.aueb.carpooling.model.view.driver.createRoute.CreateRouteView;

public class DriverRaitingPresenter {
    private DriverRatingDAO driverRatingDAO;

    DriverRaitingView view;
     public DriverRaitingPresenter(DriverRatingDAO driverRatingDAO){
         this.driverRatingDAO=driverRatingDAO;
     }

    public void setView(DriverRaitingView view) {
        this.view = view;
    }

    public DriverRaitingView getView(){
        return this.view;
    }

    public void onCreateRate(String username) {

    }
}
