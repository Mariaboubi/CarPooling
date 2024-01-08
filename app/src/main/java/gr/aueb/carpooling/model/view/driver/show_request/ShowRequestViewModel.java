package gr.aueb.carpooling.model.view.driver.show_request;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.SubrouteDAOmemory;

public class ShowRequestViewModel extends ViewModel {
    private final ShowRequestPresenter presenter;
    /**
     * Initializes the presenter by passing new daos as parameters
     */
    public ShowRequestViewModel()
    {
        presenter = new ShowRequestPresenter(new SubrouteDAOmemory());
    }
    /**
     * @return returns the presenter that stores the data
     */
    public ShowRequestPresenter getPresenter() {
        return presenter;
    }
}
