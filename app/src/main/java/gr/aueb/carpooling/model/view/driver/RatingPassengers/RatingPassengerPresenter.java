package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import gr.aueb.carpooling.model.Driver;
import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.User;
import gr.aueb.carpooling.model.contact.Address;
import gr.aueb.carpooling.model.contact.EmailAddress;
import gr.aueb.carpooling.model.contact.Money;
import gr.aueb.carpooling.model.contact.ZipCode;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExitedRouteView;

public class RatingPassengerPresenter {
    RatingPassengerView view;
    private PassengerRatingDao passengerRatingDao;

    private ArrayList<PassengerRating> ratings;


    public RatingPassengerPresenter(PassengerRatingDao passengerRatingDao)
    {
        this.passengerRatingDao = passengerRatingDao;
        ratings = new ArrayList<>();
    }


    public void setView(RatingPassengerView view) {
        this.view = view;
    }

    public RatingPassengerView getView() {
        return view;
    }

    public void setPassengerRAtingList() {
        ratings = (ArrayList<PassengerRating>) passengerRatingDao.findAll();
    }
    /**
     *  Ελεγχουμε εαν η λίστα με τις διαδρομες είναι άδεια
     *  για να τα προβάλουμε ή να δείξουμε μήνυμα οτι δεν υπάρχουν διαδρομες
     */
    public void onChangeLayout() {
        if (ratings.isEmpty()) {
            view.ShowNoPassengers();
        }
        else {
            view.ShowPassengers();
        }
    }
    /**
     * Καλεί την μέθοδο του view που μας πηγαίνει στο προηγούμενο activity που μας κάλεσε
     */
    /**
     * Επιστρέφει την λίστα με τις διαδρομές
     * @return η λίστα με τις διαδρομες
     */
    public ArrayList<PassengerRating> getPassengerRatingList() {
        return ratings;
    }


}


