package gr.aueb.carpooling.model.view.passenger.front_page;

import gr.aueb.carpooling.model.dao.PassengerDAO;
import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;

public class PassengerFrontPagePresenter {
    private final PassengerDAO passengerDAO;
    PassengerFrontPageView view;


    public PassengerFrontPagePresenter(PassengerDAOmemory passengerDAOmemory) {
        this.passengerDAO = passengerDAOmemory;
    }

    public PassengerFrontPageView getView() {
        return view;
    }

    public void setView(PassengerFrontPageView view) {
        this.view = view;
    }

    public boolean authenticateAttributePassenger(int id) {
        return passengerDAO.findPassenger(id);
    }
}
