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

    public boolean authenticateAttributePassenger(String username) {
//        view.showErrorMessage("Id in passenger", String.valueOf(id));
        boolean b = passengerDAO.find(username);
        String str = String.valueOf(b);
//        view.showErrorMessage("Id in passenger", String.valueOf(b));
        return passengerDAO.find(username);
    }

    public boolean authenticateAttributeDriver(String username) {
//        view.showErrorMessage("Id in driver", String.valueOf(id));
        boolean b = driverDAO.find(username);
        String str = String.valueOf(b);
//        view.showErrorMessage("Id in driver", username);
        return driverDAO.find(username);
    }
}
