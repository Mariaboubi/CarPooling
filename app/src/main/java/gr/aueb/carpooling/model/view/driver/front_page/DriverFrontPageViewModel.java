package gr.aueb.carpooling.model.view.driver.front_page;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;

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
