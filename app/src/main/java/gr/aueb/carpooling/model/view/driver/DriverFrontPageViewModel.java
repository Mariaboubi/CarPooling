package gr.aueb.carpooling.model.view.driver;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionPresenter;

public class DriverFrontPageViewModel extends ViewModel {
    DriverFrontPagePresenter presenter;

    /**
     * Initializes the presenter and passes a new instance of driver DAO and passenger DAO for it to use.
     */
    public DriverFrontPageViewModel() {
        presenter = new DriverFrontPagePresenter(new DriverDAOmemory());
    }

    /**
     * Returns the presenter to the classes that contain the information.
     *
     * @return the instance of the presenter created above
     */
    public DriverFrontPagePresenter getPresenter() {
        return this.presenter;
    }

}
