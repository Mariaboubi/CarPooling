package gr.aueb.carpooling.model.view.driver.front_page;

import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;

public class DriverFrontPagePresenter {
    DriverFrontPageView view;

    public DriverFrontPagePresenter(DriverDAOmemory driverDAOmemory) {
    }
    public DriverFrontPageView getView() {
        return view;
    }
    public void setView(DriverFrontPageView view) {
        this.view = view;
    }

}

