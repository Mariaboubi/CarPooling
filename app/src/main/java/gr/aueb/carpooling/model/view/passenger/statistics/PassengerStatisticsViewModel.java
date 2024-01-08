package gr.aueb.carpooling.model.view.passenger.statistics;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class PassengerStatisticsViewModel extends ViewModel {

    PassengerStatisticsPresenter presenter;
    /**
     * Initializes the presenter and passes a new object of route DAO for use.
     */
    public PassengerStatisticsViewModel(){
        presenter = new PassengerStatisticsPresenter(new RouteDAOmemory());
    }
    /**
     * Returns the presenter to the classes that contain the information.
     * @return The instance of the presenter created above.
     */
    public PassengerStatisticsPresenter getPresenter()
    {
        return presenter;
    }
}
