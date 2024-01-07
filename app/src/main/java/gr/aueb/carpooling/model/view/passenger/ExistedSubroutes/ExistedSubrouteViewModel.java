package gr.aueb.carpooling.model.view.passenger.ExistedSubroutes;

import androidx.lifecycle.ViewModel;


import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.passenger.ExistedSubroutes.ExistedSubroutePresenter;

public class ExistedSubrouteViewModel   extends ViewModel {

    ExistedSubroutePresenter presenter;
    /**
     * Αρχικοποιεί τον presenter περνώντας του σαν παραμέτρους νέα daos που θα χρησιμοποιήσει
     */
    public ExistedSubrouteViewModel()
    {
        presenter = new ExistedSubroutePresenter(new SubrouteDAOmemory());
    }
    /**
     *
     * @return επιστρέφει τον presenter που έχουμε αποθηκεύσει τα δεδομένα
     */
    public ExistedSubroutePresenter getPresenter() {
        return presenter;
    }
}
