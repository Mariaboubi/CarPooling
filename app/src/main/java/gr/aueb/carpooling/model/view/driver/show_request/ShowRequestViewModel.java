package gr.aueb.carpooling.model.view.driver.show_request;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.View;
import gr.aueb.carpooling.model.view.driver.ExistedRoutes.ExitedRoutePresenter;

public class ShowRequestViewModel extends ViewModel {
    private final ShowRequestPresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public ShowRequestViewModel()
    {
        presenter = new ShowRequestPresenter(new SubrouteDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public ShowRequestPresenter getPresenter() {
        return presenter;
    }
}
