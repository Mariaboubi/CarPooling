package gr.aueb.carpooling.model.view.passenger.DriverRaiting;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverRatingDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerRatingDAOmemory;
import gr.aueb.carpooling.model.view.driver.RatingPassengers.RatingPassengerPresenter;

public class DriverRaitingViewModel extends ViewModel {
    DriverRaitingPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public DriverRaitingViewModel()
    {
        presenter = new DriverRaitingPresenter(new DriverRatingDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public DriverRaitingPresenter getPresenter() {
        return presenter;
    }
}
