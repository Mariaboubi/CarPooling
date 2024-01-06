package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import android.app.AlertDialog;
import android.content.Intent;

import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;

import gr.aueb.carpooling.model.PassengerRating;

import gr.aueb.carpooling.model.dao.PassengerRatingDao;

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

    public void setPassengerRatingList() {
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


