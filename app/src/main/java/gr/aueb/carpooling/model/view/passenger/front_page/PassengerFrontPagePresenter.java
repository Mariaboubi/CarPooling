package gr.aueb.carpooling.model.view.passenger.front_page;

import gr.aueb.carpooling.model.memoryDao.PassengerDAOmemory;

public class PassengerFrontPagePresenter {
    PassengerFrontPageView view;

    public PassengerFrontPagePresenter(PassengerDAOmemory passengerDAOmemory) {
    }

    public PassengerFrontPageView getView() {
        return view;
    }

    public void setView(PassengerFrontPageView view) {
        this.view = view;
    }

}
