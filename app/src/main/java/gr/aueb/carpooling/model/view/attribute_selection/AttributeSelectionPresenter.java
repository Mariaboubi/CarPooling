package gr.aueb.carpooling.model.view.attribute_selection;

import gr.aueb.carpooling.model.dao.DriverDAO;
import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.DriverDAOmemory;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;
import gr.aueb.carpooling.model.view.LogIn.LogInView;


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

    public boolean authenticateAttributePassenger(int id) {
        return passengerDAO.findPassenger(id);
    }

    public boolean authenticateAttributeDriver(int id) {
        return driverDAO.findDriver(id);
    }
}
