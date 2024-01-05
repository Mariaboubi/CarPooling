package gr.aueb.carpooling.model.view.subroute;

import org.threeten.bp.LocalDateTime;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.PassengerDAO;

import gr.aueb.carpooling.model.dao.SubrouteDAO;


public class SubroutePresenter {

    private SubrouteDAO subrouteDAO;
    private PassengerDAO passengerDAO;

    SubrouteView view;
    private int PasengerId;

    private Passenger passenger;

    public SubroutePresenter(PassengerDAO passengerDAO, SubrouteDAO subrouteDAO) {
        this.passengerDAO= passengerDAO;
        this.subrouteDAO = subrouteDAO;
    }

    public void onCreateSubRoute(String username) {
        boolean isEmpty = false;
        HashMap<String, String> details = view.getSubRouteDetails();

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
        }else if (details.get("PickUp Street").length() < 2 ) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο Street.");
        } else if (details.get("PickUp Street Number").length() < 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό.");
        }else if (details.get("PickUp City").length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο City");
        } else if (details.get("PickUp ZipCode").length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 2 και πάνω ψηφία στον Ταχυδρομικό κώδικα(ZipCode).");}
        else {
            ZipCode zipCode= new ZipCode(details.get("ZipCode"),0.0,0.0);
            Address destination= new Address(details.get("Street"),details.get("Street Number"),details.get("City"),zipCode,"Greece");
            Address pickUpPoint = new Address(details.get("PickUp Street"),details.get("PickUp Street Number"),details.get("PickUp City"),zipCode,"Greece");
            Subroute subroute = new Subroute(destination,pickUpPoint, LocalDateTime.of(2023, 10, 28, 16, 30));
            passenger= passengerDAO.findByUsername(username);
            //subrouteDAO.save(passenger,subroute);
            //passenger.addRoute();
            view.showRouteAddedMessage();
        }
    }


    public void setPassenger(int id){
        passenger= passengerDAO.find(id);
    }

    public void setView(SubrouteView view) {
        this.view = view;
    }

    public SubrouteView getView(){
        return this.view;
    }

    public Passenger getPassenger(){
        return this.passenger;
    }



}
