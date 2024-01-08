package gr.aueb.carpooling.model.view.passenger.subroute;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;


public class SubrouteViewModel extends ViewModel {
    SubroutePresenter presenter;
    /**
     * Initializes the presenter and passes a new object of passenger DAO and subroute DAO for use.
     */
    public SubrouteViewModel(){
        presenter = new SubroutePresenter(new PassengerDAOmemory(), new SubrouteDAOmemory());
    }

    /**
     * Returns the presenter to the classes that contain the information.
     * @return The instance of the presenter created above.
     */
    public SubroutePresenter getPresenter(){
        return this.presenter;
    }
}
