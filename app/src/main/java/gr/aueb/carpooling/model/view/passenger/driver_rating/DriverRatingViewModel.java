package gr.aueb.carpooling.model.view.passenger.driver_rating;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverRatingDAOmemory;

public class DriverRatingViewModel extends ViewModel {
    DriverRatingPresenter presenter;

    public DriverRatingViewModel()
    {
        presenter = new DriverRatingPresenter(new DriverRatingDAOmemory());
    }

    public DriverRatingPresenter getPresenter() {
        return presenter;
    }
}
