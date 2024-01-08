package gr.aueb.carpooling.model.view.driver.existed_routes;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class ExistedRouteViewModel extends ViewModel {
    private final ExistedRoutePresenter presenter;
    /**
     * Initializes the presenter by passing new dao's as parameters
     */
    public ExistedRouteViewModel() {
        presenter = new ExistedRoutePresenter(new RouteDAOmemory());
    }

    /**
     * @return returns the presenter that stores the data
     */
    public ExistedRoutePresenter getPresenter() {
        return presenter;
    }
}
