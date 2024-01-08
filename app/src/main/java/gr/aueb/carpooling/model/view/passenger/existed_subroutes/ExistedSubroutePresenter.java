package gr.aueb.carpooling.model.view.passenger.existed_subroutes;


import java.util.ArrayList;

import gr.aueb.carpooling.model.Passenger;
import gr.aueb.carpooling.model.Subroute;
import gr.aueb.carpooling.model.dao.RouteDAO;
import gr.aueb.carpooling.model.dao.SubrouteDAO;
import gr.aueb.carpooling.model.memoryDao.RouteDAOmemory;

public class ExistedSubroutePresenter {

    ExistedSubrouteView view;

    private final RouteDAO routeDAO = new RouteDAOmemory();

    private ArrayList<Subroute> subroutes;

    public ExistedSubroutePresenter(SubrouteDAO subrouteDAO) {
        subroutes = new ArrayList<>();
    }

    /**
     * Sets our view object to use the methods of its interface.
     *
     * @param view An instance of the view.
     */
    public void setView(ExistedSubrouteView view) {
        this.view = view;
    }

    /**
     * Fills the list with the subroutes of the specific passenger.
     */
    public void setSubrouteList(Passenger passenger) {
        subroutes = (ArrayList<Subroute>) routeDAO.findSubroutesByPassenger(passenger);
    }

    /**
     * Checks if the list of subroutes is empty to display them or show a message that there are no subroutes.
     */
    public void onChangeLayout() {
        if (subroutes.isEmpty()) {
            view.ShowNoSubroutes();
        } else {
            view.ShowSubroutes();
        }
    }

    /**
     * Returns the list of subroutes.
     *
     * @return The list of subroutes.
     */
    public ArrayList<Subroute> getSubrouteList() {
        return subroutes;
    }


}
