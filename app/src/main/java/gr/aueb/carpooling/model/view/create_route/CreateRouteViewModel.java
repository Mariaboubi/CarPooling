package gr.aueb.carpooling.model.view.create_route;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class CreateRouteViewModel extends ViewModel {
    CreateRoutePresenter presenter;
    /**
     * Αρχικοποιεί τον presenter και του περνάει ένα νέο αντικείμενο τύπου owner dao και restaurant dao για να χρησιμποιήσει
     */
    public CreateRouteViewModel(){
        presenter = new CreateRoutePresenter(new DriverDAOmemory(), new RouteDAOmemory());
    }
    /**
     * Επιστρέφει τον presenter στις κλάσεις όπου περιέχει τις πληροφορίες
     * @return το instance του presenter που δημιουργήσαμε παραπάνω
     */
    public CreateRoutePresenter getPresenter(){
        return this.presenter;
    }
}
