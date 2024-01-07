package gr.aueb.carpooling.model.view.passenger.subroute;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;


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
