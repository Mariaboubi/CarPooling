package gr.aueb.carpooling.model.view.passenger.search_route;
import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;


public class SearchRouteViewModel extends ViewModel {
    private final SearchRoutePresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public SearchRouteViewModel()
    {
        presenter = new SearchRoutePresenter(new RouteDAOmemory(), new SubrouteDAOmemory(),new PassengerDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public SearchRoutePresenter getPresenter() {
        return presenter;
    }
}
