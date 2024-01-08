package gr.aueb.carpooling.model.view.driver.rating_passengers;

import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;

import gr.aueb.carpooling.model.PassengerRating;
import gr.aueb.carpooling.model.Route;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.dao.PassengerRatingDao;

public class RatingPassengerPresenter {
    RatingPassengerView view;
    private final PassengerDAO passengerDao;

    private final PassengerRatingDao passengerRatingDao;

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
     * Checks if the list of passengers is empty
     * to display or show a message that there are no passengers
     */
    public void onChangeLayout() {
        if (passengers.isEmpty()) {
            view.ShowNoPassengers();
        } else {
            view.ShowPassengers();
        }
    }

    /**
     * Returns the list of passengers
     *
     * @return The list of passengers
     */
    public ArrayList<Passenger> getPassengerList() {
        return passengers;
    }

}



