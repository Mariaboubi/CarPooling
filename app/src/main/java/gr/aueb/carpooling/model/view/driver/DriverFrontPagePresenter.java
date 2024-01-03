package gr.aueb.carpooling.model.view.driver;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionPresenter;
import gr.aueb.carpooling.model.view.attribute_selection.AttributeSelectionView;

public class DriverFrontPagePresenter {
    private DriverDAO driverDAO;
    DriverFrontPageView view;


    public DriverFrontPagePresenter(DriverDAOmemory driverDAOmemoery) {
        this.driverDAO = driverDAOmemoery;
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

