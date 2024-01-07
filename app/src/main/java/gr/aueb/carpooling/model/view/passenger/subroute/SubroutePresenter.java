package gr.aueb.carpooling.model.view.passenger.subroute;

import org.threeten.bp.LocalDateTime;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;


public class SubroutePresenter {

    private SubrouteDAO subrouteDAO;
    private PassengerDAO passengerDAO;

    SubrouteView view;

    private Passenger passenger;

    public SubroutePresenter(PassengerDAO passengerDAO, SubrouteDAO subrouteDAO) {
        this.passengerDAO = passengerDAO;
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
            view.showErrorMessage("Error!", "Please fill all the fields.");
        } else if (streetDest.length() < 2) {
            view.showErrorMessage("Error!", "Destination Street can't be less than 3 characters.");
        } else if (Integer.parseInt(numberDest) <= 0) {
            view.showErrorMessage("Error!", "Destination Street number must be a positive number.");
        } else if (CityDest.length() < 2) {
            view.showErrorMessage("Error!", "Destination City can't be less than 3 characters.");
        } else if (ZipCodeDest.length() != 5) {
            view.showErrorMessage("Error!", "Destination Zip code must be a five digit number.");
        } else if (streetPick.length() < 2) {
            view.showErrorMessage("Error!", "Pick-up Point  Street can't be less than 3 characters.");
        } else if (Integer.parseInt(numberPick) <= 0) {
            view.showErrorMessage("Error!", "Pick-up Point Street number must be a positive number.");
        } else if (CityPick.length() < 2) {
            view.showErrorMessage("Error!", "Pick-up Point City can't be less than 3 characters");
        } else if (ZipCodePick.length() != 5) {
            view.showErrorMessage("Error!", "Pick-up Point Zip code must be a five digit number.");
        } else if (!date.contains("T")) {
            view.showErrorMessage("Error!", "Incorrect date format. Please, check the example.");
        } else {
            ZipCode zipCode1 = new ZipCode(ZipCodeDest, 0.0, 0.0);
            Address destination = new Address(streetDest, numberDest, CityDest, zipCode1, "Greece");
            ZipCode zipCode2 = new ZipCode(ZipCodePick, 0.0, 0.0);
            Address pickUpPoint = new Address(streetPick, numberPick, CityPick, zipCode2, "Greece");
            Subroute subroute = new Subroute(destination, pickUpPoint, LocalDateTime.parse(date));
            subrouteDAO.save(subroute);
            view.showRouteAddedMessage(subroute);
        }
    }

    public void setPassenger(int id) {
        passenger = passengerDAO.find(id);
    }

    public void setView(SubrouteView view) {
        this.view = view;
    }

    public SubrouteView getView() {
        return this.view;
    }

    public Passenger getPassenger() {
        return this.passenger;
    }
}
