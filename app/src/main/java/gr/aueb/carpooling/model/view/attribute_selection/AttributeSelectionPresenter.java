package gr.aueb.carpooling.model.view.attribute_selection;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;


public class AttributeSelectionPresenter {

    private PassengerDAO passengerDAO;
    private DriverDAO driverDAO;
    AttributeSelectionView view;

    public AttributeSelectionPresenter(DriverDAOmemory driverDAOmemory, PassengerDAOmemory passengerDAOmemory) {
        this.passengerDAO = passengerDAOmemory;
        this.driverDAO = driverDAOmemory;
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

