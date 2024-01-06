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
        String streetDest = view.StreetDest();
        String numberDest = view.NumberDest();
        String CityDest = view.CityDest();
        String ZipCodeDest = view.ZipCodeDest();
        String streetPick = view.StreetPick();
        String numberPick = view.NumberPick();
        String CityPick = view.CityPick();
        String ZipCodePick = view.ZipCodePick();

        String date = view.Date();

        if (streetDest.isEmpty() || numberDest.isEmpty() || CityDest.isEmpty() || ZipCodeDest.isEmpty() || date.isEmpty() ||
                streetPick.isEmpty() || numberPick.isEmpty() || CityPick.isEmpty() || ZipCodePick.isEmpty()) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε όλα τα πεδία!.");
        } else if (streetDest.length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο Street του Destination.");
        } else if (Integer.parseInt(numberDest) < 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό στο νουμερο του δρομου  του Destination.");
        } else if (CityDest.length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο City  του Destination");
        } else if (ZipCodeDest.length() != 5) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 5 ψηφία στον Ταχυδρομικό κώδικα(ZipCode) του Destination");
        } else if (streetPick.length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο Street του Pick up Point.");
        } else if (Integer.parseInt(numberPick) < 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε Θετικό αριθμό στο νουμερο του δρομου του Pick up Point.");
        } else if (CityPick.length() < 2) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε απο 3 και πάνω χαρακτήρες στο City του Pick up Point");
        } else if (ZipCodePick.length() != 5) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε 5 ψηφία στον Ταχυδρομικό κώδικα(ZipCode) του Pick up Point");
        } else if (!date.contains("T")) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε την ημερομηνια σθμφωνα με το παραδειγμα");
        }else{
            ZipCode zipCode1 = new ZipCode(ZipCodeDest, 0.0, 0.0);
            Address destination = new Address(streetDest, numberDest, CityDest, zipCode1, "Greece");
            ZipCode zipCode2 = new ZipCode(ZipCodePick, 0.0, 0.0);
            Address pickUpPoint = new Address(streetPick, numberPick, CityPick, zipCode2, "Greece");
            Subroute subroute = new Subroute(destination, pickUpPoint, LocalDateTime.parse(date));
            subrouteDAO.save(subroute);
            view.showRouteAddedMessage(subroute);
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
