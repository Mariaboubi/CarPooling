package gr.aueb.carpooling.model.view.driver.rating_passengers;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;

public class RatingPassengersViewModel extends ViewModel {
    RatingPassengerPresenter presenter;

    /**
     * Initializes the presenter by passing new daos as parameters
     */
    public RatingPassengersViewModel()
    {
        presenter = new RatingPassengerPresenter(new PassengerDAOmemory(), new PassengerRatingDAOmemory());
    }

    /**
     * @return returns the presenter that stores the data
     */
    public RatingPassengerPresenter getPresenter() {
        return presenter;
    }
}


