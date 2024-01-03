package gr.aueb.carpooling.model.view.passenger;

import androidx.lifecycle.ViewModel;


import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.driver.DriverFrontPagePresenter;

public class PassengerFrontPageViewModel extends ViewModel {
    PassengerFrontPagePresenter presenter;

    /**
     * Initializes the presenter and passes a new instance of driver DAO and passenger DAO for it to use.
     */
    public PassengerFrontPageViewModel() {
        presenter = new PassengerFrontPagePresenter(new PassengerDAOmemory());
    }

    /**
     * Returns the presenter to the classes that contain the information.
     *
     * @return the instance of the presenter created above
     */
    public PassengerFrontPagePresenter getPresenter() {
        return this.presenter;
    }
}