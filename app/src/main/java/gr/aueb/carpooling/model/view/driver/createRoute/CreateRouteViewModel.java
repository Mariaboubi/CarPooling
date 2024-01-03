package gr.aueb.carpooling.model.view.driver.createRoute;

import androidx.lifecycle.ViewModel;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class CreateRouteViewModel extends ViewModel {

    CreateRoutePresenter presenter;

    public CreateRouteViewModel(){
        presenter = new CreateRoutePresenter(new DriverDAOmemory(), new RouteDAOmemory());
    }

    public CreateRoutePresenter getPresenter(){
        return this.presenter;
    }

}
