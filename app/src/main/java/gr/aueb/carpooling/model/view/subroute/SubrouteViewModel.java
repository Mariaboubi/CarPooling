package gr.aueb.carpooling.model.view.subroute;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;
import gr.aueb.carpooling.model.view.create_route.CreateRoutePresenter;

public class SubrouteViewModel extends ViewModel {
    SubroutePresenter presenter;
    /**
     * Αρχικοποιεί τον presenter και του περνάει ένα νέο αντικείμενο τύπου owner dao και restaurant dao για να χρησιμποιήσει
     */
    public SubrouteViewModel(){
        presenter = new SubroutePresenter(new PassengerDAOmemory(), new SubrouteDAOmemory());
    }

    /**
     * Επιστρέφει τον presenter στις κλάσεις όπου περιέχει τις πληροφορίες
     * @return το instance του presenter που δημιουργήσαμε παραπάνω
     */
    public SubroutePresenter getPresenter(){
        return this.presenter;
    }
}
