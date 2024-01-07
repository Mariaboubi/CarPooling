package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;

import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;

public class RatingPassengerPresenter {
    RatingPassengerView view;
    private PassengerDAO passengerDao;

    private PassengerRatingDao passengerRatingDao;

    private ArrayList<Passenger> passengers;


    public RatingPassengerPresenter(PassengerDAO passengerDao, PassengerRatingDao passengerRatingDao) {
        this.passengerDao = passengerDao;
        this.passengerRatingDao = passengerRatingDao;
        passengers = new ArrayList<>();
    }


    public void setView(RatingPassengerView view) {
        this.view = view;
    }

    public RatingPassengerView getView() {
        return view;
    }

    public void setPassengerList(Route route) {
        passengers = passengerDao.findAllByRoute(route);
        ArrayList<PassengerRating> route_ratings = passengerRatingDao.findAllByRoute(route);

        /* filter out passengers that already have a rating in the route*/
        for (PassengerRating rating : route_ratings) {
            passengers.remove(rating.getPassenger());
        }
    }

    /**
     * Ελεγχουμε εαν η λίστα με τις διαδρομες είναι άδεια
     * για να τα προβάλουμε ή να δείξουμε μήνυμα οτι δεν υπάρχουν διαδρομες
     */
    public void onChangeLayout() {
        if (passengers.isEmpty()) {
            view.ShowNoPassengers();
        } else {
            view.ShowPassengers();
        }
    }
    /**
     * Καλεί την μέθοδο του view που μας πηγαίνει στο προηγούμενο activity που μας κάλεσε
     */
    /**
     * Επιστρέφει την λίστα με τις διαδρομές
     *
     * @return η λίστα με τις διαδρομες
     */
    public ArrayList<Passenger> getPassengerList() {
        return passengers;
    }


}



