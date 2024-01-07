package gr.aueb.carpooling.model.view.driver.RatingPassengers;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;

public class RatingPassengersViewModel extends ViewModel {
    RatingPassengerPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public RatingPassengersViewModel()
    {
        presenter = new RatingPassengerPresenter(new PassengerDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public RatingPassengerPresenter getPresenter() {
        return presenter;
    }
}


