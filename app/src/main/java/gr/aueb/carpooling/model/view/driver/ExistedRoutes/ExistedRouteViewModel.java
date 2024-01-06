package gr.aueb.carpooling.model.view.driver.ExistedRoutes;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class ExistedRouteViewModel extends ViewModel {
    private final ExitedRoutePresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public ExistedRouteViewModel()
    {
        presenter = new ExitedRoutePresenter(new RouteDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public ExitedRoutePresenter getPresenter() {
        return presenter;
    }
}
