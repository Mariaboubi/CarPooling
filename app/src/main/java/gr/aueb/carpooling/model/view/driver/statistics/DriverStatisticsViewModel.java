package gr.aueb.carpooling.model.view.driver.statistics;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class DriverStatisticsViewModel extends ViewModel {

    DriverStatisticsPresenter presenter;
    /**
     * Initializes the presenter and passes a new instance of owner DAO and restaurant DAO for use.
     */
    public DriverStatisticsViewModel(){
        presenter = new DriverStatisticsPresenter(new RouteDAOmemory());
    }
    /**
     * Returns the presenter in classes where it contains the information.
     *
     * @return the instance of the presenter created above
     */
    public DriverStatisticsPresenter getPresenter()
    {
        return presenter;
    }
}
