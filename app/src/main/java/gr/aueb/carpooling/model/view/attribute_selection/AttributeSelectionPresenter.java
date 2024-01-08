package gr.aueb.carpooling.model.view.attribute_selection;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;


public class AttributeSelectionPresenter {

    private final PassengerDAO passengerDAO;
    private final DriverDAO driverDAO;
    AttributeSelectionView view;

    public AttributeSelectionPresenter(DriverDAO driverDAO, PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
        this.driverDAO = driverDAO;
    }

    public AttributeSelectionView getView() {
        return view;
    }
    public void setView(AttributeSelectionView view) {
        this.view = view;
    }

    public boolean authenticateAttributePassenger(String username) {
        return passengerDAO.find(username);
    }

    public boolean authenticateAttributeDriver(String username) {
        return driverDAO.find(username);
    }
}

