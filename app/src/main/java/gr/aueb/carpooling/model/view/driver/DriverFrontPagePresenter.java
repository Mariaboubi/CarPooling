package gr.aueb.carpooling.model.view.driver;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;

public class DriverFrontPagePresenter {
    private DriverDAO driverDAO;
    DriverFrontPageView view;


    public DriverFrontPagePresenter(DriverDAOmemory driverDAOmemory) {
        this.driverDAO = driverDAOmemory;
    }

    public DriverFrontPageView getView() {
        return view;
    }
    public void setView(DriverFrontPageView view) {
        this.view = view;
    }

    public boolean authenticateAttributeDriver(int id) {
        return driverDAO.findDriver(id);
    }
}

