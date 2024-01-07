package gr.aueb.carpooling.model.view.passenger.DriverRating;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverRatingDAOmemory;

public class DriverRatingViewModel extends ViewModel {
    DriverRatingPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public DriverRatingViewModel()
    {
        presenter = new DriverRatingPresenter(new DriverRatingDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public DriverRatingPresenter getPresenter() {
        return presenter;
    }
}
